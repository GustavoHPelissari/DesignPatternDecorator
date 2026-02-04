package decorators;

import personagens.Personagem;

public abstract class DecoratorPersonagem implements Personagem
{
    protected Personagem personagemDecorator;

    public DecoratorPersonagem(Personagem personagemDecorator)
    {
        this.personagemDecorator = personagemDecorator;
    }

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
