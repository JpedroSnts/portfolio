import { useEffect, useState } from "react";
import useWindowSize from "../../hooks/useWindowSize";
import IProject from "../../types/entities/IProject";
import ItemList from "./ItemList";
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

    useEffect(() => {
        setList(projects);
    }, [projects]);

    return (
        <>
            <div className={s.Content}>
                <div className={s.List}>
                    {list.map((item) => (
                        <ItemList item={item} key={item.id} />
                    ))}
                </div>
            </div>
        </>
    )
}

export default List;