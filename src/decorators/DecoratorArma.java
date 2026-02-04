package decorators;

import personagens.Personagem;

public class DecoratorArma extends DecoratorPersonagem
{
    private String tipoArma;
    private double buffAtaque;
    private double debuffVelocidade;

    public DecoratorArma(Personagem personagem, String tipoArma) {
        super(personagem);
        this.tipoArma = tipoArma;

        switch(tipoArma.toLowerCase()) {
            case "espada":
                this.buffAtaque = 10.0;
                this.debuffVelocidade = -2;
                break;
            case "arco":
                this.buffAtaque = 15.0;
                this.debuffVelocidade = -3;
                break;
            case "adaga":
                this.buffAtaque = 6;
                this.debuffVelocidade = 0;
            case "cajado":
                this.buffAtaque = 20.0;
                this.debuffVelocidade = -3;
                break;
            case "machado":
                this.buffAtaque = 25.0;
                this.debuffVelocidade = -4;
                break;
            default:
                this.buffAtaque = 0.0;
        }
    }

    @Override
    public String getDescrisao()
    {
        return super.getDescrisao() + String.format(" + %s (Ataque +%.1f)", tipoArma, buffAtaque);
    }

    @Override
    public double getPoderAtaque() {
        return super.getPoderAtaque() + buffAtaque;
    }
}
