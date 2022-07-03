package com.thaleswell.bankapp.services.banking;

/** This interface declares the methods that are expected to be done by the
 *  banking machinery such as accepting a deposit or dispensing cash for a
 *  withdrawal.
 * 
 * @author michael
 *
 */
public interface IBankService {
    /** Call the machinery's subroutine for dispensing cash.
     * 
     * @param withdrawal
     */
    public void dispenseCash(double withdrawal);
    
    
    /** Call the machinery's subroutine for accepting a deposit.
     * 
     * @param deposit The amount that machine should expect to receive.
     * @return true if the amount received by the machinery matches the expected
     *         deposit. false otherwise.
     */
    public boolean acceptDeposit(double deposit);
}
