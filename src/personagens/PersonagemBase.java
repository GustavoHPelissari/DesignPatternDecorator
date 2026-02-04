package personagens;

//ConcreteComponent
public class PersonagemBase implements Personagem
{
    private String nome;
    private Classe classe;

    public enum Classe
    {
        HUMANO, ELFO, ORC, DEMONIO, ANJO, GOBLIN, ANAO
    }

    public PersonagemBase(String nome, Classe classe)
    {
        this.nome = nome;
        this.classe = classe;
    }

    @Override
    public String getDescrisao() {
        return String.format("%s - %s (Nível Básico)", nome, classe);
    }

    @Override
    public double getPoderAtaque()
    {
        return switch (this.classe)
        {
            case HUMANO -> 10.0;
            case ELFO -> 12.0;
            case ORC -> 15.0;
            case DEMONIO -> 15.0;
            case ANJO -> 14.0;
            case GOBLIN -> 9.0;
            case ANAO -> 12.0;
        };
    }

    @Override
    public double getPoderDefesa()
    {
        return switch (this.classe)
        {
            case HUMANO -> 10.0;
            case ELFO -> 10.0;
            case ORC -> 12.0;
            case DEMONIO -> 13.0;
            case ANJO -> 14.0;
            case GOBLIN -> 5.0;
            case ANAO -> 15.0;
        };
    }

    @Override
    public double getVelocidade()
    {
        return switch (this.classe)
        {
            case HUMANO -> 12.0;
            case ELFO -> 15.0;
            case ORC -> 9.0;
            case DEMONIO -> 12.0;
            case ANJO -> 15.0;
            case GOBLIN -> 20.0;
            case ANAO -> 10.0;
        };
    }

    @Override
    public double getVida()
    {
        return switch (this.classe)
        {
            case HUMANO -> 100.0;
            case ELFO -> 130.0;
            case ORC -> 175.0;
            case DEMONIO -> 175.0;
            case ANJO -> 150.0;
            case GOBLIN -> 100.0;
            case ANAO -> 140.0;
        };
    }
}
