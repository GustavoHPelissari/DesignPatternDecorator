package decorators;

import personagens.Personagem;

public class DecoratorHabilidadeEspecial extends DecoratorPersonagem
{
    private final HabilidadeEspecial habilidadeEspecial;
    private double[] bonus; // [Ataque, Defesa, Velocidade, Vida]

    public DecoratorHabilidadeEspecial(Personagem personagem, HabilidadeEspecial habilidadeEspecial)
    {
           super(personagem);
           this.habilidadeEspecial = habilidadeEspecial;

           switch (habilidadeEspecial)
           {
               case ATAQUEFURTIVO -> this.bonus = new double[]{10.0,5.0,25.0,0.0};
               case DEFESACOLOSSAL -> this.bonus = new double[]{0.0,25,-4,50};
               case FURIABERSERKER -> this.bonus = new double[]{25,-4,10,30};
               case CURADIVINA -> this.bonus = new double[]{-4,0.0,10,100};
               case COLUNADECHAMAS -> this.bonus = new double[]{40,-4,0.0,-40};
           }
    }

    @Override
    public String getDescrisao()
    {
        return personagemDecorator.getDescrisao() + String.format(" + Habilidade Especial: %s", habilidadeEspecial.getNomeHabilidade());
    }

    @Override
    public double getPoderAtaque()
    {
        return personagemDecorator.getPoderAtaque() + bonus[0];
    }

    @Override
    public double getPoderDefesa()
    {
        return personagemDecorator.getPoderDefesa() + bonus[1];
    }

    @Override
    public double getVelocidade()
    {
        return personagemDecorator.getVelocidade() + bonus[2];
    }

    @Override
    public double getVida()
    {
        return personagemDecorator.getVida() + bonus[3];
    }

    public enum HabilidadeEspecial
    {
        ATAQUEFURTIVO("Ataque Furtivo"),
        DEFESACOLOSSAL( "Defesa Colossal"),
        FURIABERSERKER("Fúria Berseker"),
        CURADIVINA( "Cura Divina"),
        COLUNADECHAMAS("Coluna de Chamas");

        private final String nomeHabilidade;

        HabilidadeEspecial(String nomeHabilidade)
        {
            this.nomeHabilidade = nomeHabilidade;
        }


        public String getNomeHabilidade()
        {
            return nomeHabilidade;
        }
    }


}
