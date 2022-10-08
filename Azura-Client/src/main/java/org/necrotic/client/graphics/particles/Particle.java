package org.necrotic.client.graphics.particles;

public class Particle {

    private int age = 0;
    private int color;
    private float size;
    private Vector velocity;
    private float alpha;

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public boolean isDead() {
        return dead;
    }

    private boolean dead = false;
    public void tick() {
        if (definition == null) return;
        age++;
        if (age >= definition.getLifespan()) {
            dead = true;
            return;
        }
       
        color += definition.getColorStep();
        size += definition.getSizeStep();
        position.addLocal(velocity);
        velocity.addLocal(definition.getVelocityStep());
        alpha += definition.getAlphaStep();    
        
    }

    public ParticleDefinition getDefinition() {
        return definition;
    }

    private ParticleDefinition definition = null;

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    private Vector position;

    public Particle(ParticleDefinition pd, Vector position, int definitionID) {
        this(pd.getStartColor(), pd.getStartSize(), pd.getStartVelocity(definitionID).clone(), pd.getSpawnShape().getPoint(ParticleDefinition.RANDOM).addLocal(position), pd.getStartAlpha());
        this.definition = pd;
    }
    public Particle(int color, float size, Vector velocity, Vector position, float alpha) {
        this.color = color;
        this.size = size;
        this.velocity = velocity;
        this.position = position;
        this.alpha = alpha;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
}

