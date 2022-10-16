import IHomeProps from "../../types/props/IHomeProps";
import { api } from "../api";

async function getProps(): Promise<IHomeProps> {
	return (await api().get("/mock/home")).data;
}

export { getProps };
