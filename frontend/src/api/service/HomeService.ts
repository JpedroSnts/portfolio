import IHomeProps from "../../types/props/IHomeProps";
import { api } from "../api";

async function getProps(): Promise<IHomeProps> {
	return (await api().get("/template/home")).data;
}

export { getProps };
