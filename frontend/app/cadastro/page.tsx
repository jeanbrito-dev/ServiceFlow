'use client';

import Link from "next/link";
import { useState } from "react";

const input =
    "w-full rounded-md border border-olive-300 px-4 py-3 text-lg outline-none focus:border-olive-600";

const button =
    "rounded-md bg-olive-600 px-8 py-3 text-xl text-white hover:bg-olive-700 transition-colors";

export default function Register() {
    const [registered, setRegistered] = useState(false);

    function handleRegister(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();

        localStorage.setItem("isLogged", "true");
        setRegistered(true);
    }

    if (registered) {
        return (
            <div className="flex min-h-screen flex-col items-center justify-center">
                <h1 className="text-6xl text-olive-800">
                    Cadastro realizado
                </h1>

                <h2 className="mt-2 text-2xl font-bold text-olive-500">
                    Sua conta foi criada com sucesso
                </h2>

                <Link
                    href="/dashboard"
                    className="mt-8 text-olive-600 hover:text-olive-800"
                >
                    Ir para o dashboard
                </Link>
            </div>
        );
    }

    return (
        <div className="flex min-h-screen flex-col items-center justify-center">
            <h1 className="text-6xl text-olive-800">
                Service Flow
            </h1>

            <h2 className="mt-2 text-2xl font-bold text-olive-500">
                Registro
            </h2>

            <form
                onSubmit={handleRegister}
                className="mt-10 flex w-full max-w-md flex-col gap-5"
            >
                <input
                    type="text"
                    placeholder="Nome"
                    className={input}
                    required
                />

                <input
                    type="email"
                    placeholder="E-mail"
                    className={input}
                    required
                />

                <input
                    type="password"
                    placeholder="Senha"
                    className={input}
                    required
                />

                <input
                    type="password"
                    placeholder="Confirmar senha"
                    className={input}
                    required
                />

                <button
                    type="submit"
                    className={button}
                >
                    Criar conta
                </button>
            </form>

            <Link
                href="/login"
                className="text-olive-600 hover:text-olive-800"
            >
                Já possui uma conta?
            </Link>
        </div>
    );
}