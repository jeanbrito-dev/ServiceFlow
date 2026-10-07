import { Chamado } from "@/types/chamado";

const api = process.env.NEXT_PUBLIC_API_URL;

export async function getChamados() {
    try {
        const response = await fetch(api + "/chamados");

        const data: Chamado[] = await response.json();

        return {
            data,
            status: response.status,
        };

    } catch (error) {
        console.error("Erro ao buscar chamados:", error);
        throw error;
    }
};

export async function getChamadoById(id: number) {
    try {
        const response = await fetch(api + "/chamados/" + id);

        const data: Chamado = await response.json();

        return {
            data,
            status: response.status,
        };
    } catch (error) {
        console.error(`Erro ao buscar chamado de id ${id}: ${error}`);
        throw error;
    }
}

