import React, { useEffect } from 'react';
import classes from './Home.module.scss'
import ReferenceForm from '../../components/ReferenceForm/ReferenceForm';
import Graph from '../../components/Graph/Graph';
import LeftBar from '../../components/LeftBar/LeftBar';
import TopBar from '../../components/TopBar/TopBar';
import ContentLayout from '../../components/ContentLayout/ContentLayout';
import CurrentValues from '../../components/CurrentValues/CurrentValues';
import Alert from '../../components/Alert/Alert';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../../app/store';
import { setAlert } from '../../slices/referenceValueSlice';

export default function Home() {
    const isAlert = useSelector((state: RootState) => state.reference.isAlert);
    const message = useSelector((state: RootState) => state.reference.messages);
    const referenceValue = useSelector((state: RootState) => state.reference.data);
    const dispatch = useDispatch();
    
    useEffect(() => {
        console.log('worked');
        
        if (message) {
            const refValues = referenceValue.reduce<{ [key: string]: {val: string, name: string}}>((acc, item) => {
                acc[item.id] = {val: item.reference.value, name: item.name};
                return acc;
              }, {});
            
            if (refValues[message.id].val !== message.value) {
                dispatch(setAlert({ isOn: true, sensor: refValues[message.id].name }));
            }
        }
    }, [message]) 

    return (
        <>
            {isAlert ? <Alert/> : null}
            <div className={classes.App}>
                <LeftBar/>
                <div className={classes.RightContainer}>
                    <TopBar/>
                    <ContentLayout>
                        <div style={{
                            display: 'flex',
                            flexDirection: 'column',
                            gap: '10px',
                            width: '100%',
                        }}>
                            <CurrentValues/>
                            <ReferenceForm/>
                        </div>
                    </ContentLayout>
                </div>
            </div>
        </>
    )
}