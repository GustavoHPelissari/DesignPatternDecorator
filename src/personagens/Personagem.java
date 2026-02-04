package personagens;

/*
      Component -> Interface base que define as operações comuns
 */
public interface Personagem
{
    String getDescrisao();
    double getPoderAtaque();
    double getPoderDefesa();
    double getVelocidade();
    double getVida();


    // Method default com implementação concreta
    default void mostrarStatus()
    {
        //╔╗ ╠╣ ╬ ╦ ═ ║ ▶ ╚╝ ╩
        System.out.println("╔"+"═════════════════╦"+"═".repeat(50)+"╗");
        System.out.println("║    • DESCRIÇÃO     ║ ▶  " + getDescrisao());
        System.out.println("╠"+"═════════════════╬"+"═".repeat(50)+"╣");
        System.out.println("║   • VIDA           ║ ▶  " + getVida());
        System.out.println("║   • ATAQUE         ║ ▶  " + getPoderAtaque());
        System.out.println("║   • DEFESA         ║ ▶  " + getPoderDefesa());
        System.out.println("║   • VELOCIDADE     ║ ▶  " + getVelocidade());
        System.out.println("╠"+"═════════════════╠"+"═".repeat(50)+"╣");
        System.out.println("║   • PODER TOTAL    ║ ▶  " + (getPoderAtaque() + getPoderDefesa() + getVida()));
        System.out.println("╚"+"═════════════════╩"+"═".repeat(50)+"╝");
    }
}
