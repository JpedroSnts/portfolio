import ICertificado from "../../types/entities/ICertificado";
import ITechnology from "../../types/entities/ITechnology";
import { api } from "../api";

async function getTechnologies(): Promise<ITechnology[]> {
	return (await api().get("/tecnologia")).data;
}

async function getTechnology(id: string | number): Promise<ITechnology | undefined> {
	return (await getTechnologies()).find(t => t.id === id);
}

async function getCertificados(): Promise<ICertificado[]> {
	return (await api().get("/certificado")).data;
}

async function getCertificadosByTechnology(id: string | undefined): Promise<ICertificado[]> {
	return (await api().get("/certificado/tecnologia/" + id)).data;
}

export { getTechnologies, getCertificados, getTechnology, getCertificadosByTechnology };
