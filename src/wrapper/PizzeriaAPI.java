package wrapper;
/**
 * Main api
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */

public interface PizzeriaAPI extends CreatePizzeria, UpdatePizzeria
{
    String className();
}
