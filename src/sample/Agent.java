package sample;

public class Agent
{
    private int agentId;
    private String agentFirstName;
    private String agentMiddleInitial;
    private String agentLastName;
    private String agentBusinessPhone;
    private String agentEmail;
    private String agentPosition;
    private int agentAgencyId;

    public Agent(int agentId, String agentFirstName, String agentMiddleInitial, String agentLastName, String agentBusinessPhone, String agentEmail, String agentPosition, int agentAgencyId)
    {
        this.agentId = agentId;
        this.agentFirstName = agentFirstName;
        this.agentMiddleInitial = agentMiddleInitial;
        this.agentLastName = agentLastName;
        this.agentBusinessPhone = agentBusinessPhone;
        this.agentEmail = agentEmail;
        this.agentPosition = agentPosition;
        this.agentAgencyId = agentAgencyId;
    }

    public Agent(String agentFirstName, String agentLastName)
    {
        this.agentFirstName = agentFirstName;
        this.agentLastName = agentLastName;
    }

    // getters
    public int getAgentId() { return agentId; }
    public String getAgentFirstName() { return agentFirstName; }
    public String getAgentMiddleInitial() { return agentMiddleInitial; }
    public String getAgentLastName() { return agentLastName; }
    public String getAgentBusinessPhone() { return agentBusinessPhone; }
    public String getAgentEmail() { return agentEmail; }
    public String getAgentPosition() { return agentPosition; }
    public int getAgentAgencyId() { return agentAgencyId; }

    // setters
    public void setAgentId(int agentId) { this.agentId = agentId; }
    public void setAgentFirstName(String agentFirstName) { this.agentFirstName = agentFirstName; }
    public void setAgentMiddleInitial(String agentMiddleInitial) { this.agentMiddleInitial = agentMiddleInitial; }
    public void setAgentLastName(String agentLastName) { this.agentLastName = agentLastName; }
    public void setAgentBusinessPhone(String agentBusinessPhone) { this.agentBusinessPhone = agentBusinessPhone; }
    public void setAgentEmail(String agentEmail) { this.agentEmail = agentEmail; }
    public void setAgentPosition(String agentPosition) { this.agentPosition = agentPosition; }
    public void setAgentAgencyId(int agentAgencyId) { this.agentAgencyId = agentAgencyId; }

    // toString
    @Override
    public String toString()
    {
        return agentFirstName + " " + agentLastName;
    }
}
