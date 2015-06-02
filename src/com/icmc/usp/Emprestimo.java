package com.icmc.usp;

public class Emprestimo {
        private String livro;
        private String usuario;
        private String data;

        public Emprestimo(String livro, String usuario, String data) {
                this.livro = livro;
                this.usuario = usuario;
                this.data = data;
        }

        public String getLivro() {
                return this.livro;
        }

        public String getUsuario() {
                return this.usuario;
        }

        public String getData() {
                return this.data;
        }

}
