import React from 'react';
import classes from './ContentLayout.module.scss'

interface ContentLayoutProps {
    children: React.ReactNode;
}

export default function ContentLayout({ children }: ContentLayoutProps) {
    return (
        <div className={classes.ContentLayout}>
            {children}
        </div>
    )
}