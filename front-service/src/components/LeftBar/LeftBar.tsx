import React from 'react';
import classes from './LeftBar.module.scss';
import { ReactComponent as SettingsIcon } from './img/settings.svg';
import logoIcon from './img/logo.png'
import graphIcon from './img/graph.png'

function LeftBar() {
    return (
        <div className={classes.LeftBar}>
                <a className={classes.Button} href="/home">
                    <img src={logoIcon} className={classes.PngIcon}/>
                </a>
            <div className={classes.MenuButtons}>
                <a className={classes.Button} href="/graph">
                    <img src={graphIcon} className={classes.PngIcon} />
                </a>                             
            </div>
        </div>
    )
}

export default LeftBar;