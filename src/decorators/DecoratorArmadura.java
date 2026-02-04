package decorators;

import personagens.Personagem;

public class DecoratorArmadura extends DecoratorPersonagem
{
    private final String tipoArmadura;
    private final double buffDefesa;
    private final double debuffVelocidade;


    public DecoratorArmadura(Personagem personagem, String tipoArmadura) {
        super(personagem);
        this.tipoArmadura = tipoArmadura;

        switch (tipoArmadura.toLowerCase()) {
            case "leve":
                this.buffDefesa = 10.0;
                this.debuffVelocidade = -2.0;
                break;
            case "media":
                this.buffDefesa = 15;
                this.debuffVelocidade = -3.0;
                break;
            case "pesada":
                this.buffDefesa = 20;
                this.debuffVelocidade = -6.0;
                break;
            default:
                this.buffDefesa = 0;
                this.debuffVelocidade = 0;

        }
    }

    @Override
    public String getDescrisao()
    {
        return super.getDescrisao() + String.format(" + Armadura %s (Defesa +%.1f, Velocidade %.1f)", tipoArmadura, buffDefesa, debuffVelocidade);
    }

    @Override
    public double getPoderDefesa() {
        return super.getPoderDefesa() + buffDefesa;
    }

    @Override
    public double getVelocidade() {
        return super.getVelocidade() + debuffVelocidade;
    }
}
