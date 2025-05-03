import React from 'react';
import classes from './CreateTransactionModal.module.scss';
import { useSelector, useDispatch } from 'react-redux';
import type { RootState } from '../../app/store';
import {ReactComponent as CrossIcon} from './img/Cross.svg'
import Input from '../UI/Input/Input';

function CreateTransactionModal() {
    const onOverlayClick = () => {
       
    }

    const onContentClick = (event: React.MouseEvent<HTMLDivElement>) => {
        event.stopPropagation();
    }

    return (
        <div onClick={onOverlayClick} className={classes.ModalOverlay}>
            <div onClick={onContentClick} className={classes.ModalContent}>
                <div className={classes.Container}>
                    <h1>New operation</h1>
                </div>
            </div>
        </div>
    )
}

export default CreateTransactionModal;