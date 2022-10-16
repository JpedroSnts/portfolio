import s from "./style.module.css";

interface IButtonProps {
    disabled?: boolean | undefined;
    type?: 'submit' | 'reset' | 'button' | undefined;
    children?: any;
    onClick?: Function;
}

const Button = ({ disabled, type, children, onClick }: IButtonProps) => {
    function click () {
        return typeof onClick === "undefined" ? () => { } : onClick;
    }

    return (
        <button type={type} disabled={disabled} className={s.Button} onClick={click}>{children}</button>
    )
}

export default Button;