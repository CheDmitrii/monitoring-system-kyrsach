import React from 'react';
import classes from './Alert.module.scss'
import Button from '../UI/Button/Button';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../../app/store';
import { setAlert } from '../../slices/referenceValueSlice';

export default function Alert() {
    const sensorName = useSelector((state: RootState) => state.reference.alertMessage);
    const dispatch = useDispatch();

    return (
        <div className={classes.Alert}>
            <div className={classes.Wrapper}>
                <div className={classes.Container}>
                    <h1>Внимание!</h1>
                    <span>Значения датчика {sensorName} вышли за допустимый предел.</span>
                    <Button onClick={() => dispatch(setAlert({isOn: false}))}>Закрыть уведомление</Button>
                </div>
            </div>
        </div>
    )
}