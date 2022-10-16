import { ChangeEventHandler } from "react";
import s from "./style.module.css";


interface InputProps {
    placeholder: string;
    name?: string;
    id?: string;
    type?: 'text' | 'email' | 'number' | 'textarea';
    onChange?: ChangeEventHandler<HTMLTextAreaElement | HTMLInputElement>
    value?: any;
}

const Input = ({ type, name, id, placeholder, onChange, value }: InputProps) => {
    if (type === 'textarea') {
        return (
            <textarea name={name} id={id} className={s.Input} placeholder={placeholder} required onChange={onChange} value={value}></textarea>
        )
    }
    return (
        <input type={`${type ? type : 'text'}`} name={name} id={id} className={s.Input} required placeholder={placeholder} onChange={onChange} value={value} />
    )

}

export default Input;