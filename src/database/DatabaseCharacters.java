package database;

import decorators.DecoratorArma;
import decorators.DecoratorArmadura;
import personagens.Personagem;
import personagens.PersonagemBase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseCharacters
{
    private static DatabaseCharacters instance;
    private List<Personagem> personagens;
    private int proximoId;


    private DatabaseCharacters()
    {
        this.personagens = new ArrayList<>();
        this.proximoId = 1;
        carregarPersonagensExemplo(); // Opcional: carregar alguns exemplos
    }

    public static DatabaseCharacters getInstance()
    {
        if (instance == null)
        {
            instance = new DatabaseCharacters();
        }
        return instance;
    }

    public void adicionarPersonagem(Personagem personagem)
    {
        personagens.add(personagem);
        System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println(" ┃ ✅ Personagem salvo no banco de dados! Personagens totais: " + personagens.size() + "                                                                 ┃");
        System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");
    }

    public List<Personagem> getTodosPersonagens()
    {
        return new ArrayList<>(personagens);
    }

    public Personagem getPersonagemPorIndice(int indice)
    {
        if (indice >= 0 && indice < personagens.size())
        {
            return personagens.get(indice);
        }
        return null;
    }

    public boolean removerPersonagem(int indice)
    {
        if (indice >= 0 && indice < personagens.size())
        {
            Personagem removido = personagens.get(indice);
            personagens.remove(indice);
            System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println(" ┃ ✅ Personagem removido com sucesso!                                                                                                    ┃");
            System.out.println(" ┃ Removido: " + removido.getDescrisao());
            System.out.println(" ┃ Personagens restantes: " + personagens.size() + "                                                                                         ┃");
            System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");
            return true;
        }

        System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println(" ┃ ⚠️  Índice inválido! Não foi possível remover o personagem.                                                                           ┃");
        System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");
        return false;
    }

    public void limparBancoDeDados()
    {
        int totalRemovidos = personagens.size();
        personagens.clear();
        System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println(" ┃ 🗑️  " + totalRemovidos + " personagens removidos! Banco de dados limpo.                                                                 ┃");
        System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");
    }

    public int getTotalPersonagens()
    {
        return personagens.size();
    }

    public void mostrarTodosPersonagens()
    {
        if (personagens.isEmpty())
        {
            System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println(" ┃ 📭 Nenhum personagem salvo no banco de dados.                                                                                         ┃");
            System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");
            return;
        }

        System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println(" ┃ 📋 PERSONAGENS SALVOS (" + personagens.size() + ")                                                                                        ┃");
        System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");

        for (int i = 0; i < personagens.size(); i++)
        {
            Personagem p = personagens.get(i);
            System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println(" ┃ " + (i + 1) + ". " + p.getDescrisao());
            System.out.println(" ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println(" ┃ Ataque: " + p.getPoderAtaque() +
                    " | Defesa: " + p.getPoderDefesa() +
                    " | Vida: " + p.getVida() +
                    " | Velocidade: " + p.getVelocidade());

            double poderTotal = p.getPoderAtaque() + p.getPoderDefesa() + p.getVida();
            System.out.println(" ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println(" ┃  Poder Total: " + poderTotal);
            System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");
        }
    }

    private void carregarPersonagensExemplo() {
        PersonagemBase.Classe[] classes = PersonagemBase.Classe.values();
        String[] nomes = {"Aragorn", "Legolas", "Gimli", "Gandalf", "Frodo", "Boromir"};

        for (int i = 0; i < Math.min(nomes.length, classes.length); i++)
        {
            Personagem p = new PersonagemBase(nomes[i], classes[i]);
            // Adiciona alguns equipamentos aleatórios
            if (i % 2 == 0)
            {
                p = new DecoratorArma(p, "espada");
            }
            if (i % 3 == 0)
            {
                p = new DecoratorArmadura(p, "media");
            }
            personagens.add(p);
        }
    }

    public void compararPersonagens(int indice1, int indice2)
    {
        if (indice1 < 0 || indice1 >= personagens.size() || indice2 < 0 || indice2 >= personagens.size())
        {
            System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println(" ┃ ⚠️  Índices inválidos!                                                                                                                ┃");
            System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");
            return;
        }

        Personagem p1 = personagens.get(indice1);
        Personagem p2 = personagens.get(indice2);

        System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println(" ┃ ⚖️  COMPARAÇÃO DE PERSONAGENS                                                                                                          ┃");
        System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");

        System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println(" ┃ PERSONAGEM 1 (#" + (indice1 + 1) + "):                                                                                                 ┃");
        System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");
        p1.mostrarStatus();

        System.out.println("\n ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println(" ┃ PERSONAGEM 2 (#" + (indice2 + 1) + "):                                                                                                 ┃");
        System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");
        p2.mostrarStatus();

        System.out.println("\n ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println(" ┃ 🎯 RESUMO DA COMPARAÇÃO                                                                                                                ┃");
        System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");

        System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println(" ┃ ESTATÍSTICAS:                                                                                                                        ┃");
        System.out.println(" ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        compararEstatisticaComBorda("⚔️  ATAQUE", p1.getPoderAtaque(), p2.getPoderAtaque());
        compararEstatisticaComBorda("🛡️  DEFESA", p1.getPoderDefesa(), p2.getPoderDefesa());
        compararEstatisticaComBorda("🏃 VELOCIDADE", p1.getVelocidade(), p2.getVelocidade());
        compararEstatisticaComBorda("❤️  VIDA", p1.getVida(), p2.getVida());
        System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");

        double poderTotal1 = p1.getPoderAtaque() + p1.getPoderDefesa() + p1.getVida();
        double poderTotal2 = p2.getPoderAtaque() + p2.getPoderDefesa() + p2.getVida();

        System.out.println(" ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println(" ┃ PODER TOTAL:                                                                                                                         ┃");
        System.out.println(" ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
        System.out.printf(" ┃ Personagem 1: %-10.1f                                                                                                  ┃\n", poderTotal1);
        System.out.printf(" ┃ Personagem 2: %-10.1f                                                                                                  ┃\n", poderTotal2);
        System.out.println(" ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");

        if (poderTotal1 > poderTotal2)
        {
            System.out.println(" ┃ ✅ Personagem 1 é mais forte!                                                                                                     ┃");
        }
        else if (poderTotal2 > poderTotal1)
        {
            System.out.println(" ┃ ✅ Personagem 2 é mais forte!                                                                                                     ┃");
        }
        else
        {
            System.out.println(" ┃ ⚖️  Os personagens são igualmente fortes!                                                                                         ┃");
        }
        System.out.println(" ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n");
    }

    private void compararEstatisticaComBorda(String nome, double valor1, double valor2)
    {
        String simbolo = "=";
        if (valor1 > valor2) simbolo = ">";
        else if (valor1 < valor2) simbolo = "<";

        System.out.printf(" ┃ %-15s: %6.1f %s %6.1f", nome, valor1, simbolo, valor2);

        int espacos = 75 - (nome.length() + 22);
        for (int i = 0; i < espacos; i++) {
            System.out.print(" ");
        }
        System.out.println("┃");
    }

    private void compararEstatistica(String nome, double valor1, double valor2)
    {
        String simbolo = "=";
        if (valor1 > valor2) simbolo = ">";
        else if (valor1 < valor2) simbolo = "<";

        System.out.printf("%-12s: %6.1f %s %6.1f%n",
                nome, valor1, simbolo, valor2);
    }
}
