package com.icmc.usp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Biblioteca {
        private List<Usuario> usuarios;
        private List<Livro> livros;
        private List<Emprestimo> emprestimos;
        private GregorianCalendar data;
        private BufferedReader br;

        public Biblioteca() {
                this.usuarios = new ArrayList<Usuario>();
                this.livros = new ArrayList<Livro>();
                this.emprestimos = new ArrayList<Emprestimo>();
                this.data = new GregorianCalendar();
                this.data.setLenient(false);
                this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public void go() throws IOException {
                int menu;
                boolean sair = false;

                while(!sair) {
                        System.out.println("Biblioteca de Dantalian\n");
                        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy"); // formatacao da data
                        System.out.print("Data: " + date.format(this.data.getTime())); // recebe data
                        System.out.println("\n1. Cadastrar Usuario" +
                                           "\n2. Cadastrar Livro" +
                                           "\n3. Cadastrar Emprestimo" +
                                           "\n4. Cadastrar Devolucao" +
                                           "\n5. Listar Usuarios" +
                                           "\n6. Listar Livros" +
                                           "\n7. Listar Emprestimos" +
                                           "\n8. Sair");
                        menu = Integer.parseInt(br.readLine());
                        switch(menu) {
                                case 1:
                                        addUsuario(); // FEITO
                                        break;
                                case 2:
                                        addLivro(); // FEITO
                                        break;
                                case 3:
                                        addEmprestimo(); // FEITO
                                        break;
                                case 4:
                                        addDevolucao(); // FEITO
                                        break;
                                case 5:
                                        listarUsuarios(); // FEITO
                                        break;
                                case 6:
                                        listarLivros(); // FEITO
                                        break;
                                case 7:
                                        listarEmprestimos(); // FEITO
                                        break;
                                case 8:
                                        sair = true; // FEITO
                                        break;
                        }
                }
        }

        public void addUsuario() throws IOException {
                System.out.print("Tipo (1 - Estudante / 2 - Professor / 3 - Comunidade): ");
                int tipo = br.read();
                System.out.print("Nome: ");
                String nome = br.readLine();
                Usuario addUsuario = new Usuario(tipo,nome);
                this.usuarios.add(addUsuario);
                FileWriter fw = new FileWriter("recursos/usuarios.csv", true); // abre
                BufferedWriter bw = new BufferedWriter(fw); // dá buffer
                bw.write(tipo + "," + nome + "\n");  // escreve no csv
                bw.close(); // fecha
        }

        public void addLivro() throws IOException {
                System.out.print("Tipo (1 - Texto / 2 - Geral): ");
                int tipo = br.read();
                System.out.print("Nome: ");
                String nome = br.readLine();
                System.out.print("Autor: ");
                String autor = br.readLine();
                Livro addLivro = new Livro(tipo,nome,autor,true);
                this.livros.add(addLivro);
                FileWriter fw = new FileWriter("recursos/livros.csv", true); // abre
                BufferedWriter bw = new BufferedWriter(fw); // dá buffer
                bw.write(tipo + "," + nome + "," + autor + "," + true + "\n"); // escreve no csv
                bw.close(); // fecha
        }

        public void addEmprestimo() throws IOException {
        	int tipo = 0;
                System.out.print("Usuario: ");
                String usuario = br.readLine();
                for (Usuario user: this.usuarios) {
                        if ((!user.getNome().equals(usuario)) || (user.getSuspensao()!=0) || (!user.adicionarLivro())) {
                                System.out.println("Usuario nao pode efetuar emprestimo");
                        }
                        else {
                                tipo = user.getTipo();
                        }
                }
                System.out.print("Livro: ");
                String livro = br.readLine();
                for (Livro liv: this.livros) {
                        if ((!liv.getNome().equals(livro)) || (!liv.isDisponivel()) || ((liv.getTipo()==1) && (tipo==3))) {
                                System.out.println("Livro nao pode ser emprestado");
                        }
                        else {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                String data = sdf.format(this.data.getTime());
                                Emprestimo addEmprestimo = new Emprestimo(livro, usuario, data);
                                this.emprestimos.add(addEmprestimo);
                                FileWriter fw = new FileWriter("recursos/emprestimos.txt", true); // abre
                                BufferedWriter bw = new BufferedWriter(fw); // dá buffer
                                bw.write(livro + "," + usuario + "," + data + "\n"); // escreve no csv
                                bw.close(); // fecha
                        }
                }


        }

        public void addDevolucao() throws IOException {
                System.out.print("Livro: ");
                String nome = br.readLine();
                for (Emprestimo emp: this.emprestimos) {
                        if (!emp.getLivro().equals(nome)) {
                                System.out.println("Livro nao foi emprestado");
                                return;
                        }
                        else {
                                this.emprestimos.remove(1);
                                return;
                        }
                }
        }

        public void removerEmprestimo(String nome) throws IOException {
                File emprestimos = new File("recursos/emprestimos.csv"); // cria arquivo principal
                File aux = new File("recursos/aux.csv"); // cria arquivo auxiliar
                BufferedReader leitor = new BufferedReader(new FileReader(emprestimos)); // dá buffer
                BufferedWriter escritor = new BufferedWriter(new FileWriter(aux)); // dá buffer
                String linha = null; // inicia primeira linha
                while (((linha = leitor.readLine()) != null)) {
                        if (!linha.trim().contains(nome)) {
                                escritor.write(linha);
                                escritor.newLine();
                                escritor.flush();
                        }
                }
                escritor.close();
                leitor.close();
                System.gc();
        }

        public void listarUsuarios() throws IOException {
                if (this.usuarios.isEmpty()) {
                        System.out.println("Nao ha usuarios");
                }
                else {
                        for (Usuario user: this.usuarios) {
                                System.out.print(user.getNome() + " :: ");
                                System.out.print(user.getTipo() + "\n");
                        }
                }
        }

        public void listarLivros() throws IOException {
                if (this.livros.isEmpty()) {
                        System.out.println("Nao ha livros");
                }
                else {
                        for (Livro liv: this.livros) {
                                System.out.print(liv.getNome() + " :: ");
                                System.out.print(liv.getAutor() + " :: ");
                                System.out.print(liv.getTipo() + "\n");
                        }
                }
        }

        public void listarEmprestimos() throws IOException {
                if (this.emprestimos.isEmpty()) {
                        System.out.println("Nao ha emprestimos");
                }
                else {
                        for (Emprestimo emp: this.emprestimos) {
                                System.out.print(emp.getLivro() + " :: ");
                                System.out.print(emp.getUsuario() + " :: ");
                                System.out.print(emp.getData() + "\n");
                        }
                }
        }
}