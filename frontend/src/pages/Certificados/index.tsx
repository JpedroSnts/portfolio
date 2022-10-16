import { useEffect, useRef, useState } from "react";
import { getCertificados, getCertificadosByTechnology, getTechnologies } from "../../api/service/CertificadosService";
import Select from "../../components/Form/Select";
import IconButton from "../../components/IconButton";
import ICertificado from "../../types/entities/ICertificado";
import ITechnology from "../../types/entities/ITechnology";
import s from "./style.module.css";

function Home () {
    const [certificados, setCertificados] = useState<ICertificado[]>([]);
    const [options, setOptions] = useState<ITechnology[]>([]);
    const ref = useRef<HTMLSelectElement>(null);

    async function onChange () {
        const selectedValue = ref.current?.selectedOptions[0].value.toLowerCase();
        if (selectedValue === "0") {
            setCertificados(await getCertificados());
            return;
        }
        const filterCertificados = await getCertificadosByTechnology(selectedValue);
        setCertificados(filterCertificados);
    }

    useEffect(() => {
        (async () => {
            setCertificados(await getCertificados());
            setOptions(await getTechnologies());
        })();
    }, []);

    return (
        <div className={s.Content}>
            <section className={s.Select}>
                <Select placeholder="Categoria" options={options} selectRef={ref} onChange={onChange} />
            </section>
            <section className={s.Certificados}>
                {certificados.map(({ id, link, icon, name }) => (
                    <IconButton icon={icon} text={name} link={link} key={id} />
                ))}
            </section>
        </div>
    );
}

export default Home;