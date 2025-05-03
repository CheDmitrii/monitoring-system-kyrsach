import React, { useEffect, useState } from 'react';
import classes from './ReferenceValue.module.scss'
import Input from '../../UI/Input/Input';

interface ReferenceValueProps {
    name: string;
    value: string;
    onChange: (newValue: string) => void;
    isSent: boolean
}

export default function ReferenceValue({ name, value, onChange, isSent }: ReferenceValueProps) {
    const [inputValue, setInputValue] = useState(value);

    const onInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        onChange(e.target.value)
        setInputValue(e.target.value)
    }

    return (
            <div className={classes.ReferenceValue}>
                <span>{name}</span>
                <Input value={inputValue} onChange={onInputChange}></Input>
                {isSent ? <span style={{
                    color: 'green',
                }}>Значение обновлено</span> : null}
            </div>
    )
}