import IContactForms from "../../types/entities/IContactForms";
import IFormContact from "../../types/entities/IFormContact";
import { api } from "../api";

async function getContactForms(): Promise<IContactForms[]> {
	return (await api().get("/mock/contato")).data;
}

async function sendEmail(form: IFormContact): Promise<any> {
	await api().post("/mock/email", form);
}

export { getContactForms, sendEmail };
