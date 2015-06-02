package com.icmc.usp;

public class Livro {
        private int tipo; // 0 para texto, 1 para geral
        private String nome;
        private String autor;
        private boolean disponivel;

        public Livro(int tipo, String nome, String autor, boolean disponivel) {
                this.tipo = tipo;
                this.nome = nome;
                this.autor = autor;
                this.disponivel = disponivel;
        }

        public void setTipo(int tipo) {
                this.tipo = tipo;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public void setAutor(String autor) {
                this.autor = autor;
        }

        public void setDisponivel(boolean disponivel) {
                this.disponivel = disponivel;
        }

        public int getTipo() {
                return this.tipo;
        }

        public String getNome() {
                return this.nome;
        }

        public String getAutor() {
                return this.autor;
        }

        public boolean isDisponivel() {
                return this.disponivel;
        }

}
