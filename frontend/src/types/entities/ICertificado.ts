import ITechnology from "./ITechnology";

export default interface ICertificado {
	id: string | number;
	nome: string;
	certificado: string;
	tecnologia: ITechnology;
}
