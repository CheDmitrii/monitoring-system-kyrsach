import React, { useEffect, useState } from 'react';
import classes from './CurrentValues.module.scss'
import { useSelector } from 'react-redux';
import { RootState } from '../../app/store';

export default function CurrentValues() {
    const referenceValues = useSelector((state: RootState) => state.reference.data);
    const message = useSelector((state: RootState) => state.reference.messages);
    const [values, setValues] = useState<{ [key: string]: {name: string, value: string}} | null>(null);    

    useEffect(() => {
        if (referenceValues)
        {
        const values = referenceValues.reduce<{ [key: string]: {name: string, value: string}}>((acc, item) => {
            acc[item.id] = {name: item.name, value: 'NA'}; // Ключ = элемент массива, значение = удвоенный элемент
            return acc;
          }, {});
        setValues(values);  
    }
    }, [referenceValues])    

    useEffect(() => {
        if (message && values) {
            setValues(prevValues => {
              if (!prevValues) return prevValues;
        
              // Создаем новый объект копированием prevValues
              return {
                ...prevValues,
                [message.id]: {
                  name: prevValues[message.id]?.name || 'Unknown',
                  value: message.value,
                },
              };
            });
          }
    }, [message])

    return (
        <div className={classes.CurrentValuesForm}>
        <div className={classes.Wrapper}>
            <div className={classes.Container}>
                <h1>Текущие значения:</h1>
                {values ? Object.entries(values).map(([key, value]) => {
                    return (
                        <div key={key}>
                            <span>{value.name}: {value.value}</span>
                        </div>  
                    )
                }) : null}
            </div>
        </div>
    </div>
    )
}