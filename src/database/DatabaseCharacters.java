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

    // Padrão Singleton para garantir uma única instância
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

    // Métodos CRUD
    public void adicionarPersonagem(Personagem personagem)
    {
        personagens.add(personagem);
        System.out.println("✅ Personagem salvo no banco de dados!");
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
            personagens.remove(indice);
            System.out.println("✅ Personagem removido com sucesso!");
            return true;
        }
        return false;
    }

    public void limparBancoDeDados()
    {
        personagens.clear();
        System.out.println("🗑️  Todos os personagens foram removidos!");
    }

    public int getTotalPersonagens()
    {
        return personagens.size();
    }

    public void mostrarTodosPersonagens()
    {
        if (personagens.isEmpty())
        {
            System.out.println("📭 Nenhum personagem salvo no banco de dados.");
            return;
        }

        System.out.println("\n" + "📋".repeat(30));
        System.out.println("PERSONAGENS SALVOS (" + personagens.size() + ")");
        System.out.println("📋".repeat(30));

        for (int i = 0; i < personagens.size(); i++)
        {
            Personagem p = personagens.get(i);
            System.out.println("\n" + (i + 1) + ". " + p.getDescrisao());
            System.out.println("   Ataque: " + p.getPoderAtaque() +
                    " | Defesa: " + p.getPoderDefesa() +
                    " | Vida: " + p.getVida());
        }
    }

    // Método para carregar alguns personagens de exemplo
    private void carregarPersonagensExemplo() {
        // Adiciona alguns personagens de exemplo
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

    // Método para comparar dois personagens
    public void compararPersonagens(int indice1, int indice2)
    {
        if (indice1 < 0 || indice1 >= personagens.size() || indice2 < 0 || indice2 >= personagens.size())
        {
            System.out.println("⚠️  Índices inválidos!");
            return;
        }

        Personagem p1 = personagens.get(indice1);
        Personagem p2 = personagens.get(indice2);

        System.out.println("\n" + "⚖️".repeat(40));
        System.out.println("COMPARAÇÃO DE PERSONAGENS");
        System.out.println("⚖️".repeat(40));

        System.out.println("\nPERSONAGEM 1:");
        System.out.println("-".repeat(40));
        p1.mostrarStatus();

        System.out.println("\nPERSONAGEM 2:");
        System.out.println("-".repeat(40));
        p2.mostrarStatus();

        System.out.println("\n" + "🎯 RESUMO DA COMPARAÇÃO:");
        System.out.println("═".repeat(40));
        compararEstatistica("ATAQUE", p1.getPoderAtaque(), p2.getPoderAtaque());
        compararEstatistica("DEFESA", p1.getPoderDefesa(), p2.getPoderDefesa());
        compararEstatistica("VELOCIDADE", p1.getVelocidade(), p2.getVelocidade());
        compararEstatistica("VIDA", p1.getVida(), p2.getVida());

        double poderTotal1 = p1.getPoderAtaque() + p1.getPoderDefesa() + p1.getVida();
        double poderTotal2 = p2.getPoderAtaque() + p2.getPoderDefesa() + p2.getVida();

        System.out.println("\n" + "🏆 PODER TOTAL:");
        System.out.println("Personagem 1: " + poderTotal1);
        System.out.println("Personagem 2: " + poderTotal2);

        if (poderTotal1 > poderTotal2)
        {
            System.out.println("✅ Personagem 1 é mais forte!");
        }
        else if (poderTotal2 > poderTotal1)
        {
            System.out.println("✅ Personagem 2 é mais forte!");
        }
        else
        {
            System.out.println("⚖️  Os personagens são igualmente fortes!");
        }
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
