package decorators;

import personagens.Personagem;

/*
        Decorator -> Classe abstrata que implementa a interface Component
        (OBS) -> Mamtém referência para um objeto Component
 */
public abstract class DecoratorPersonagem implements Personagem
{
    protected Personagem personagemDecorator;

    public DecoratorPersonagem(Personagem personagemDecorator)
    {
        this.personagemDecorator = personagemDecorator;
    }

    // Delega todas as operações para o Component enolvido por padrão
    // As subclasses podem sobrescrever os métodos que querem modificar
    @Override
    public String getDescrisao()
    {
        return personagemDecorator.getDescrisao();
    }

    @Override
    public double getPoderAtaque()
    {
        return personagemDecorator.getPoderAtaque();
    }

    @Override
    public double getPoderDefesa()
    {
        return personagemDecorator.getPoderDefesa();
    }

    @Override
    public double getVelocidade()
    {
        return personagemDecorator.getVelocidade();
    }

    @Override
    public double getVida()
    {
        return personagemDecorator.getVida();
    }
}
