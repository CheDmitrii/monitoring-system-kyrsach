import { useEffect, useState } from 'react';
import Input from '../UI/Input/Input'
import classes from './ReferenceForm.module.scss'
import Button from '../UI/Button/Button';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import axios from 'axios';
import ReferenceValue from './ReferenceValue/ReferenceValue';
import { useSelector } from 'react-redux';
import { RootState } from '../../app/store';

export default function ReferenceForm() {
    const referenceValues = useSelector((state: RootState) => state.reference.data);
    const [stompClient, setStompClient] = useState<Client | null>(null);
    const [inputValues, setInputValues] = useState<{ [id: string]: string }>({});
    const [updateSent, setUpdateSent] = useState<boolean>(false);

    useEffect(() => {
        const socket = new SockJS('http://localhost:8228/socket');
        const client = new Client({
            webSocketFactory: () => socket,
            onConnect: () => {
                console.log('WebSocket connected');
            },
            onStompError: (frame) => {
                console.error('WebSocket error:', frame);
            }
        });

        client.activate();
        setStompClient(client);

        return () => {
            client.deactivate();
        };
    }, []);

    const sendUpdate = (oldValue: string, newValue: string, id: string) => {
        if (stompClient) {
            const update = {
                id: id,
                oldValue: oldValue,
                newValue: newValue,
            };
    
            stompClient.publish({
                destination: '/app/reference/update',
                body: JSON.stringify(update),
                headers: {}
            });
            setUpdateSent(true);
            setTimeout(() => {
                setUpdateSent(false)
            }, 2000)
        } else {
            console.error('WebSocket клиент не подключен');
        }
    };

    return (
        <div className={classes.ReferenceForm}>
            <div className={classes.Wrapper}>
                <div className={classes.Container}>
                    <h1>Эталонные значения</h1>
                    {referenceValues.length !== 0 ? referenceValues.map((item, index) => {
                        return (<div key={index} className={classes.RefContainer}>
                            <ReferenceValue isSent={updateSent} name={item.name}  value={inputValues[item.id] ?? item.reference.value} 
                            onChange={(newValue) => setInputValues(prev => ({ ...prev, [item.id]: newValue }))}/>
                            <Button onClick={() => sendUpdate(item.reference.value, inputValues[item.id] ?? item.reference.value, item.reference.id)}>Изменить</Button>
                        </div>)
                    }) : null}
                </div>
            </div>
        </div>
        )
}