import React, { useState } from 'react';
import classes from './Input.module.scss'

interface InputProps {
    onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
    value: string;
    placeholder?: string;
    currency?: string;
}

export default function Input({ onChange, value, placeholder }: InputProps) {
    const [isHovered, setIsHovered] = useState(false);
    const [isFocused, setIsFocused] = useState(false);

    const style = {
        outline: isFocused ? '2px solid white' : isHovered ? '2px solid #727272' : 'none',
     }

     const focusHandler = () => {
        setIsFocused(true);
     }

    return (
            <div>
                <input className={classes.Input}
                        style={style} onFocus={focusHandler}
                        onBlur={() => setIsFocused(false)}
                        onMouseEnter={() => setIsHovered(true)}
                        onMouseLeave={() => setIsHovered(false)}
                        value={value}
                        onChange={onChange}
                        placeholder={placeholder}>
                        </input>
            </div>
    )
}