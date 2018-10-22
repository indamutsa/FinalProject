package serverUtils;
/**
 * This program creates requested object (factory pattern)
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import util.Courrier;

public class StrategyFactory
{
    public static StrategyContext getProtocol(Courrier courrier)
    {
        String message = courrier.getMessage();

        switch (message)
        {
            case "Upload":
                return new StrategyContext(new UploadPizzeriaProtocol());
            case "show":
                return new StrategyContext(new DisplayPizzeriaProtocol());
            case "Print":
                return new StrategyContext(new PrintPizzeriaProtocol());
            case "Delete":
                return new StrategyContext(new DeletePizzeriaProtocol());
            case "UpdateBP":
                return new StrategyContext(new UpdateBasePriceProtocol());
            case "UpdateOption":
                return new StrategyContext(new OptionAdditionProtocol());
            case "showlist":
                return new StrategyContext(new DisplayOptionSet());
            case "calculate":
                return new StrategyContext(new CalculatePrice());
            default:
                return null;
        }
    }
}
