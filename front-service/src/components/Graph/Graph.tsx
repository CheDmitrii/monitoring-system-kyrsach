import React, { useEffect, useMemo, useRef, useState } from 'react';
import classes from './Graph.module.scss';
import axios from 'axios';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../../app/store';
import ReactECharts from 'echarts-for-react';
import { setAlert } from '../../slices/referenceValueSlice';

export interface Journal {
  [key: string]: string;
  time: string;
}

interface ApiResponse {
  name: string;
  journal: Journal[];
}

interface GraphProps {
  name: string;
  id: string;
  reference: string;
}

export default function Graph({ name, id, reference }: GraphProps) {
  const message = useSelector((state: RootState) => state.reference.messages);
  const [journal, setJournal] = useState<Journal[]>([]);
  const [zoom, setZoom] = useState<{ startValue?: string; endValue?: string }>({});
  const [isAutoScroll, setIsAutoScroll] = useState(true);
  const dispatch = useDispatch();

  const xData = useMemo(() => journal.map(item => item.time), [journal]);
  const yData = useMemo(() => journal.map(item => Number(item[name])), [journal]);

  const chartRef = useRef<any>(null);

  useEffect(() => {
    const chartInstance = chartRef.current?.getEchartsInstance();

    if (!chartInstance) return;

    const onDataZoom = (params: any) => {
      if (params.batch && params.batch.length > 0) {
        const { startValue, endValue } = params.batch[0];
        setZoom({ startValue, endValue });
        setIsAutoScroll(false);
      }
    };

    chartInstance.on('datazoom', onDataZoom);

    return () => {
      chartInstance.off('datazoom', onDataZoom);
    };
  }, []);

  useEffect(() => {
    if (!message) return; 
    if (message.id === id)
    {        
        setJournal(prev => {
      const newEntry: Journal = {
        [name]: message.value,
        time: message.time,
      };
  
      return [...prev, newEntry]; 
    });
    }
  }, [message]);

  const referenceLineData = useMemo(() => {
    return xData.map(() => reference);
  }, [xData, reference]);

  const theme = 'white'

  const option = useMemo(
    () => ({
      backgroundColor: '#1C1C1C',
      tooltip: { trigger: 'axis' },
      grid: { left: 40, right: 20, bottom: 60, top: 30, containLabel: true },
      xAxis: {
        type: 'category',
        data: xData,
        axisLine: { lineStyle: { color: theme } },
        axisLabel: { color: theme },
      },
      yAxis: {
        type: 'value',
        axisLine: { lineStyle: { color: theme } },
        axisLabel: { color: theme },
      },
      dataZoom: [
        {
          type: 'slider',
          xAxisIndex: 0,
          filterMode: 'filter',
          zoomLock: false,
          startValue: zoom.startValue,
          endValue: zoom.endValue,
        },
        {
            type: 'inside',
            filterMode: 'filter',
            zoomLock: false,
          },
      ],
      series: [
        {
          name,
          type: 'line',
          data: yData,
          showSymbol: false,
          lineStyle: { color: '#08C400', width: 2 },
          emphasis: { focus: 'series' },
          smooth: true,
          animation: false,
          animationDurationUpdate: 0,
          animationEasingUpdate: 'linear',
        },
        {
            name: 'Reference',
            type: 'line',
            data: referenceLineData,
            showSymbol: false,
            lineStyle: { color: 'red', width: 2 },
            emphasis: { focus: 'series' },
            smooth: true,
            animation: false,
            animationDurationUpdate: 0,
            animationEasingUpdate: 'linear',
          },
      ],
    }),
    [xData, yData, name, zoom]
  );

  useEffect(() => {
    axios
      .get<ApiResponse[]>('http://localhost:8228/sensor/all-sensors')
      .then(response => {
        const sensor = response.data.find(item => item.name === name);        
        if (!sensor) {
          setJournal([]);
          return;
        }
        const journalData: Journal[] = sensor.journal.map(item => {
          return { [name]: item.value, time: item.time.split('T')[1].split('.')[0] };
        });
        setJournal(journalData);

        if (journalData.length > 0) {
          const len = journalData.length;
          const windowSize = 50;
          const startIndex = Math.max(0, len - windowSize);
          setZoom({
            startValue: journalData[startIndex].time,
            endValue: journalData[len - 1].time,
          });
          setIsAutoScroll(true);
        }
      })
      .catch(err => {
        console.error('Ошибка при загрузке данных:', err);
      });
  }, [name]);

  useEffect(() => {
    const interval = setInterval(() => {
      setJournal(prev => {
        if (prev.length === 0) return prev;

        const lastEntry = prev[prev.length - 1];
        const valueKeys = Object.keys(lastEntry).filter(key => key !== 'time');
        const valueKey = valueKeys[0];
        const value = lastEntry[valueKey];

        const now = new Date();
        const newTime = now.toTimeString().slice(0, 8);

        const newEntry: Journal = {
          [valueKey]: value,
          time: newTime,
        };

        const newJournal = [...prev, newEntry];

        if (isAutoScroll) {
          const len = newJournal.length;
          const windowSize = 50;
          const startIndex = Math.max(0, len - windowSize);
          const endIndex = len - 1;

          setZoom({
            startValue: newJournal[startIndex].time,
            endValue: newJournal[endIndex].time,
          });
        }

        return newJournal;
      });
    }, 1000);

    return () => clearInterval(interval);
  }, [isAutoScroll]);

  return (
    <div className={classes.Graph}>
      <ReactECharts ref={chartRef} option={option} style={{ width: '100%', height: '100%' }} />
    </div>
  );
}
