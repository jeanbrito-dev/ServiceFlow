'use client';

import Link from "next/link";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const input =
    "w-full rounded-md border border-olive-300 px-4 py-3 text-lg outline-none focus:border-olive-600";

const button =
    "rounded-md bg-olive-600 px-8 py-3 text-xl text-white hover:bg-olive-700 transition-colors";

export default function Login() {
    const [isLogged, setIsLogged] = useState(false);
    const router = useRouter();

    useEffect(() => {
        const logged = localStorage.getItem("isLogged");

        if (logged === "true") {
            setIsLogged(true);
        }
    }, []);

    function handleLogin(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();

        localStorage.setItem("isLogged", "true");
        setIsLogged(true);
        router.push("/dashboard");
    }

    if (isLogged) {
        return (
            <div className="flex min-h-screen flex-col items-center justify-center">
                <h1 className="text-6xl text-olive-800">
                    Login realizado
                </h1>

                <h2 className="text-2xl font-bold text-olive-500">
                    Você já realizou o login
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
                Login
            </h2>

            <form
                onSubmit={handleLogin}
                className="mt-10 flex w-full max-w-md flex-col gap-5"
            >
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

                <button
                    type="submit"
                    className={button}
                >
                    Entrar
                </button>
            </form>

            <Link
                href="/"
                className="mt-6 text-olive-600 hover:text-olive-800"
            >
                Voltar
            </Link>
        </div>
    );
}
