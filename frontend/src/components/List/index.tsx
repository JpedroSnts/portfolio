import { useEffect, useState } from "react";
import useWindowSize from "../../hooks/useWindowSize";
import IProject from "../../types/entities/IProject";
import ItemList from "./ItemList";
import Pagination from "./Pagination";
import s from "./style.module.css";

function addLeadingZeros (num: number, totalLength: number) {
    return String(num).padStart(totalLength, '0');
}

function calculatePages (width: number, size: number): number {
    if (width >= 940)
        return size - 2;
    if (width >= 640)
        return size - 1;
    return size;
}

function List ({ projects }: { projects: IProject[] }) {
    const size = useWindowSize();
    const [list, setList] = useState<IProject[]>([]);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(calculatePages(size.width, projects.length));

    function incrementPage () {
        if (page !== totalPages) setPage(page + 1);
    }

    function decrementPage () {
        if (page !== 1) setPage(page - 1);
    }

    useEffect(() => {
        setTotalPages(calculatePages(size.width, projects.length));
        setPage(1);
    }, [size]);

    useEffect(() => {
        if (projects.length === 0) return;

        if (size.width >= 940) {
            setList([projects[page - 1], projects[page], projects[page + 1]]);
            return;
        }
        if (size.width >= 640) {
            setList([projects[page - 1], projects[page]]);
            return;
        }
        setList([projects[page - 1]]);
    }, [page, size, projects])

    return (
        <>
            <div className={s.Content}>
                <div className={s.List}>
                    {list.map((item) => (
                        <ItemList item={item} key={item.id} />
                    ))}
                </div>
                <div className={s.Pagination}>
                    <p>Página {addLeadingZeros(page, 2)} de {addLeadingZeros(totalPages, 2)}</p>
                    <Pagination increment={incrementPage} decrement={decrementPage} />
                </div>
            </div>
        </>
    )
}

export default List;