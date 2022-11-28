import ITypeProject from "./ITypeProject";

export default interface IProject {
	id: string | number;
	nome: string;
	descricao: string;
	link: string;
	imagem: string;
	tipoProjeto: ITypeProject;
}
