package com.icmc.usp;

public class Usuario {
        private int tipo;
        private String nome;
        private int livros;
        private int suspensao;

        public Usuario(int tipo, String nome) {
                this.tipo = tipo; // 1 para aluno, 2 para professor, 3 para comunidade
                this.nome = nome;
                this.livros = 0;
                this.suspensao = 0;
        }

        public boolean adicionarLivro() {
                if (this.tipo == 1) { // aluno
                        if (this.livros<4) {
                                this.livros++;
                                return true;
                        }
                }
                else if (this.tipo == 2) { // professor
                        if (this.livros<6) {
                                this.livros++;
                                return true;
                        }
                }
                else {
                        if (this.livros<2) { // comunidade
                                this.livros++;
                                return true;
                        }
                }
                return false;
        }

        public boolean removerLivro() {
                if (this.livros>0) {
                        this.livros--;
                        return true;
                }
                else {
                        return false;
                }
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public void setSuspensao(int dias) {
                this.suspensao = dias;
        }

        public void setTipo(int tipo) {
                this.tipo = tipo;
        }

        public int getTipo() {
                return this.tipo;
        }

        public String getNome() {
                return this.nome;
        }

        public int getLivros() {
                return this.livros;
        }

        public int getSuspensao() {
                return this.suspensao;
        }
}
