import { ChangeEventHandler, RefObject } from "react";
import ITechnology from "../../../types/entities/ITechnology";
import s from "./style.module.css";

interface SelectProps {
    placeholder?: string;
    name?: string;
    id?: string;
    options?: ITechnology[];
    onChange?: ChangeEventHandler;
    selectRef?: RefObject<HTMLSelectElement> | undefined;
}

const Select = ({ name, id, placeholder, options, onChange, selectRef }: SelectProps) => {

    return (
        <select defaultValue={"0"} name={name} id={id} className={s.Select} required onChange={onChange} ref={selectRef}>
            {placeholder && <option value={"0"}>{placeholder}</option>}
            {options?.map(({ id, nome }) => (
                <option value={id} key={id}>{nome}</option>
            ))}
        </select>
    )

}

export default Select;