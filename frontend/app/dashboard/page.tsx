"use client";

import { useState, useEffect } from "react";
import { getChamados } from "@/service/api";
import { Chamado } from "@/types/chamado";

export default function Dashboard() {
    const [chamados, setChamados] = useState<Chamado[]>([]);

    useEffect(() => {
        async function carregarChamados() {
            try {
                const { data, status } = await getChamados();

                if (status !== 200) {
                    throw new Error(`Erro HTTP: ${status}`);
                }

                setChamados(data);
            } catch (error) {
                console.error("Erro ao buscar chamados:", error);
            }
        }

        carregarChamados();
    }, []);

    return (
        <div className="flex flex-col p-10 min-h-screen">
            <h1 className="text-3xl font-bold text-olive-500">
                Bem-vindo usuário!
            </h1>

            <div id="painel" className="flex mt-10 gap-5">
                {/* Chamados em aberto */}
                <div className="flex-1 rounded border-2 border-olive-400 px-6 pb-6">
                    <h2 className="inline-block rounded-b bg-olive-400 px-3 py-1 text-2xl font-bold text-white mb-6">
                        Chamados em aberto
                    </h2>

                    <div className="space-y-4">
                        {chamados
                            .filter((chamado) => chamado.status === "Pendente")
                            .map((chamado) => {
                                const data = new Date(chamado.dataCriacao);

                                return (
                                    <div
                                        key={chamado.id}
                                        className="rounded bg-emerald-200 p-4"
                                    >
                                        <div className="mb-2 flex justify-between">
                                            <h3 className="text-xl font-bold text-green-700">
                                                {chamado.titulo}
                                            </h3>

                                            <button className="rounded border-2 border-green-700 bg-green-700 px-2 text-white transition-colors hover:border-green-700 hover:bg-green-200 hover:text-green-700">
                                                Atender chamado
                                            </button>
                                        </div>

                                        <hr className="mb-4 rounded border-green-700" />

                                        <p className="rounded bg-teal-50 p-2 text-emerald-900">
                                            {chamado.descricao}
                                        </p>

                                        <p className="mt-2 text-right text-emerald-500">
                                            Criado em{" "}
                                            {data.toLocaleDateString("pt-BR")}
                                        </p>
                                    </div>
                                );
                            })}
                    </div>
                </div>

                {/* Meus chamados */}
                <div className="flex-1 rounded border-2 border-olive-400 px-6 pb-6">
                    <h2 className="inline-block rounded-b bg-olive-400 px-3 py-1 text-2xl font-bold text-white mb-6">
                        Meus chamados
                    </h2>

                    {/* chamados do usuário */}
                </div>
            </div>
        </div>
    );
}
