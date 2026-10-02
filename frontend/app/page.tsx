'use client';

import Link from "next/link";
import { useEffect, useState } from "react";

const button = "rounded-md bg-olive-600 text-3xl px-8 py-4 mt-15 text-white hover:bg-olive-700 transition-colors";

export default function FirstScreen() {
  const [isLogged, setIsLogged] = useState(false);

  useEffect(() => {
    const logged = localStorage.getItem("isLogged");

    if (logged === "true") {
      setIsLogged(true);
    }
  }, []);


  if (isLogged) {
    return (
      <div className="flex flex-col items-center justify-center min-h-screen">
        <h1 className="text-8xl text-olive-800">Service Flow</h1>
        <h2 className="text-3xl font-bold text-olive-500">Gereciador de chamados</h2>

        <Link href="/dashboard" className={button}>Começar</Link>
      </div>
    )
  }

  return (
    <div className="flex flex-col items-center justify-center min-h-screen">
      <h1 className="text-8xl text-olive-800">Service Flow</h1>
      <h2 className="text-3xl font-bold text-olive-500">Gereciador de chamados</h2>

      <Link href="/login" className={button}>Começar</Link>
    </div>
  );
}
