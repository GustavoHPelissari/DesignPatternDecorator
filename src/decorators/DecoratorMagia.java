package decorators;

import personagens.Personagem;

/*
        ConcreteDecorator -> Decorador concreto que adiciona comportamento de habilidade especial
 */
public class DecoratorMagia extends DecoratorPersonagem
{
    private final String tipoMagia;
    private final double buffAtaque;
    private final double buffVida;

    public DecoratorMagia(Personagem personagem, String tipoMagia)
    {
        super(personagem);
        this.tipoMagia = tipoMagia;

        switch(tipoMagia.toLowerCase())
        {
            case "fogo":
                this.buffAtaque = 30.0;
                this.buffVida = -10.0;
                break;
            case "gelo":
                this.buffAtaque = 20.0;
                this.buffVida = 0.0;
                break;
            case "cura":
                this.buffAtaque = 10.0;
                this.buffVida = 50.0;
                break;
            default:
                this.buffAtaque = 20.0;
                this.buffVida = 0.0;
        }
    }

    @Override
    public String getDescrisao()
    {
        return personagemDecorator.getDescrisao() + String.format(" + Magia de %s (Ataque +%.1f, Vida %.1f)", tipoMagia, buffAtaque, buffVida);
    }

    @Override
    public double getPoderAtaque() {
        return personagemDecorator.getPoderAtaque() + buffAtaque;
    }

    @Override
    public double getVida() {
        return personagemDecorator.getVida() + buffVida;
    }
}
