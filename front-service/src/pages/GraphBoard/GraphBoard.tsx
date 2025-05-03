import React, { useEffect } from 'react';
import classes from './GraphBoard.module.scss'
import Graph from '../../components/Graph/Graph';
import LeftBar from '../../components/LeftBar/LeftBar';
import TopBar from '../../components/TopBar/TopBar';
import ContentLayout from '../../components/ContentLayout/ContentLayout';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../../app/store';
import CurrentValues from '../../components/CurrentValues/CurrentValues';
import { setAlert } from '../../slices/referenceValueSlice';
import Alert from '../../components/Alert/Alert';

export default function GraphBoard() {
    const referenceValue = useSelector((state: RootState) => state.reference.data);    
    const isAlert = useSelector((state: RootState) => state.reference.isAlert);
    const dispatch = useDispatch();
    const message = useSelector((state: RootState) => state.reference.messages);
        
    useEffect(() => {
        console.log('worked');
        
        if (message) {
            const refValues = referenceValue.reduce<{ [key: string]: string}>((acc, item) => {
                acc[item.id] = item.reference.value;
                return acc;
                }, {});

            console.log(refValues);
            
            if (refValues[message.id] !== message.value) {
                dispatch(setAlert(true));
            }
        }
    }, [message])    


    console.log(referenceValue);
    

    return (
        <>
            {isAlert ? <Alert/> : null}
            <div className={classes.App}>
            <LeftBar/>
            <div className={classes.RightContainer}>
                <TopBar/>
                <ContentLayout>
                    <div style={{
                        width: '100%',
                        height: '100%',
                    }}>
                        <CurrentValues/>
                        <div style={{
                            display: 'flex',
                            gap: '10px',
                            marginTop: '10px',
                            height: '100%',
                            maxHeight: '500px',
                        }}>
                                {referenceValue.map((item, index) => {
                                    return <Graph key={index} name={item.name} id={item.id} reference={item.reference.value}/>
                                })}
                        </div>
                    </div>
                </ContentLayout>
            </div>
                </div>
        </>
    )
}