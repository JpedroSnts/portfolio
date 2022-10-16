import s from "./style.module.css";

interface IPaginationProps {
    increment: () => void;
    decrement: () => void;
}

function Pagination ({ increment, decrement }: IPaginationProps) {
    return (
        <div className={s.Pagination}>
            <span className={s.ChevronLeft} onClick={decrement}></span>
            <span className={s.ChevronRight} onClick={increment}></span>
        </div>
    )
}

export default Pagination;