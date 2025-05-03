import React from 'react';
import classes from './TopBar.module.scss'
import { useDispatch, useSelector } from 'react-redux';
import type { RootState } from '../../app/store';
import {ReactComponent as AddTrans } from './img/addTrans.svg';
import alertIcon from './img/alertIcon.png'


function TopBar() {
    return (
        <div className={classes.TopBar}>
            <span>Monitoring System</span>
            {/* <a className={classes.Button}>
                <img src={alertIcon}/>
            </a> */}
        </div>
    )
}

export default TopBar;