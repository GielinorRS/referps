package org.necrotic.client.graphics.particles;

import java.util.Random;

import org.necrotic.client.graphics.Sprite;

public class ParticleDefinition {

    public static final Random RANDOM = new Random(System.currentTimeMillis());

    public static ParticleDefinition[] cache = new ParticleDefinition[] {
    		// the "definition id" gets set everytime you creatwe a new instance of particle definition.
            new ParticleDefinition() {{// so this particle definition id is 0.
                // Eternal cape -cyan 0
                setStartVelocity(new Vector(0, -1, 0));
                setEndVelocity(new Vector(0, -1, 0));
                setGravity(new Vector(0, 2 / 4, 0));
                setLifespan(19);
                setStartColor(0x0089bf);//set colour here
                setSpawnRate(4);
                setStartSize(0.9f);//set size here
                setEndSize(0);
                setStartAlpha(0.080f);
                updateSteps();
                setColorStep(0x000000);
            }},
            new ParticleDefinition() {{//this is 1
                // Max cape - white 1
                setStartVelocity(new Vector(0, -1, 0));
                setEndVelocity(new Vector(0, -1, 0));
                setGravity(new Vector(0, 2 / 4, 0));
                setLifespan(19);
                setStartColor(0xc8c0a8);//set colour here
                setSpawnRate(4);
                setStartSize(0.9f);//set size here
                setEndSize(0);
                setStartAlpha(0.080f);
                updateSteps();
                setColorStep(0x000000);
            }},
            new ParticleDefinition() {{//this is 2
                // viggora - peach 2
                setStartVelocity(new Vector(0, -1, 0));
                setEndVelocity(new Vector(0, -1, 0));
                setGravity(new Vector(0, 2 / 4, 0));
                setLifespan(19);
                setStartColor(0xe94d95);//set colour here
                setSpawnRate(10);
                setStartSize(0.9f);//set size here
                setEndSize(0);
                setStartAlpha(0.080f);
                updateSteps();
                setColorStep(0x000000);
            }},
            new ParticleDefinition() {{//this is 3 and so on.
                // scthe == DONT USEy
                setStartVelocity(new Vector(0, -1, 0));
                setEndVelocity(new Vector(0, -1, 0));
                setGravity(new Vector(0, 2 / 4, 0));
                setLifespan(19);
                setStartColor(0x1d1c1c);//set colour here
                setSpawnRate(8);
                setStartSize(0.9f);//set size here
                setEndSize(0);
                setStartAlpha(0.080f);
                updateSteps();
                setColorStep(0x000000);
            }}
            ,
            new ParticleDefinition() {{//this is 4 and so on.
                // scthe == DONT USEy
            	setStartVelocity(new Vector(0, 1, 0));
                setEndVelocity(new Vector(0, 1, 0));
                setGravity(new Vector(0, 2 / 3, 0));
                setLifespan(19);
                setStartColor(0xFF0800);//set colour here
                setSpawnRate(4);
                setStartSize(1.2f);//set size here
                setEndSize(0.5f);
                setStartAlpha(0f);
                updateSteps();
                setColorStep(0x000900);
            }}
            ,
            new ParticleDefinition() {{//this is 5 and so on.
                // scythe == test2
                setStartVelocity(new Vector(0, -1, 0));
                setEndVelocity(new Vector(0, -1, 0));
                setGravity(new Vector(0, 2 / 4, 0));
                setLifespan(19);
                setStartColor(0x181616);//set colour here
                setSpawnRate(4);
                setStartSize(0.9f);//set size here
                setEndSize(0);
                setStartAlpha(0.080f);
                updateSteps();
                setColorStep(0x000000);
            }},
            new ParticleDefinition() {{//this is 6
               //NUMBER 6
            	 setStartVelocity(new Vector(0, -3, 0));
                 setEndVelocity(new Vector(0, -3, 0));
                 setGravity(new Vector(0, 2 / 4, 0));
                 setLifespan(19);
                 setStartColor(0x2e5dff);
                 setSpawnRate(11);
                 setStartSize(0.75f);
                 setEndSize(0);
                 setStartAlpha(0.035f);
                 updateSteps();
                 setColorStep(0xde6e00);
                 
            }},
            new ParticleDefinition() {{//this is 7
                //NUMBER 7
             	 setStartVelocity(new Vector(2, 2, 0));
                  setEndVelocity(new Vector(2, 2, 0));
                  setGravity(new Vector(0, 4 / 4, 0));
                  setLifespan(19);
                  setStartColor(0xffde00);
                  setSpawnRate(15);
                  setStartSize(0.60f);
                  setEndSize(0);
                  setStartAlpha(1.05f);
                  updateSteps();
                  setColorStep(0xffde00);
                  
             }},
            new ParticleDefinition() {{//this is 8

                setStartVelocity(new Vector(-2, 2, 0));
                setEndVelocity(new Vector(-2, 2, 0));
                setGravity(new Vector(0, 1 / 1, 0));
                setLifespan(19);
                setStartColor(0xffde00);
                setSpawnRate(15);
                setStartSize(0.60f);
                setEndSize(0);
                setStartAlpha(1.05f);
                updateSteps();
                setColorStep(0xffde00);

            }},
            new ParticleDefinition() {{//this is 9 and so on.
                // scthe == DONT USEy
                setStartVelocity(new Vector(0, 1, 0));
                setEndVelocity(new Vector(0, 1, 0));
                setGravity(new Vector(0, 2 / 3, 0));
                setLifespan(19);
                setStartColor(0xffff00);//set colour here
                setSpawnRate(4);
                setStartSize(1.2f);//set size here
                setEndSize(0.5f);
                setStartAlpha(0f);
                updateSteps();
                setColorStep(0xffff00);
            }}
            ,

    };

    /**
     * The size at which the particle starts.
     */
    private float startSize = 1f;
    /**
     * The size at which the particle is at the end of it's life.
     */
    private float endSize = 1f;
    /**
     * The start color of the particles.
     */
    private int startColor = 0xFFFFFFFF;
    /**
     * The end color of the particles.
     */
    private int endColor = 0xFFFFFFFF;
    /**
     * The velocity when the particle is first spawned.
     */
    private Vector startVelocity = Vector.ZERO;
    /**
     * The velocity of the particle when it dies.
     */
    private Vector endVelocity = Vector.ZERO;
    /**
     * The amount of frames a particle survives for.
     */
    private int lifespan = 1;
    /**
     * The maximum amount of particles at any given moment.
     */
    private int maxParticles = 10000;
    /**
     * The amount of particles spawned each frame.
     */
    private int spawnRate = 1;
    /**
     * The sprite of the particles.
     */
    private int zBuffer;
    private Sprite sprite;

    private float startAlpha = 1f;
    private float endAlpha = 0.05f;

    public Vector getGravity() {
        return gravity;
    }

    public void setGravity(Vector gravity) {
        this.gravity = gravity;
    }

    public int getzBuffer() {
        return zBuffer;
    }

    public void setzBuffer(int zBuffer) {
        this.zBuffer = zBuffer;
    }
    /**
     * The gravitational force for this object.
     */
    private Vector gravity;

    public float getStartAlpha() {
        return startAlpha;
    }

    public void setStartAlpha(float startAlpha) {
        this.startAlpha = startAlpha;
    }

    public float getEndAlpha() {
        return endAlpha;
    }

    public void setEndAlpha(float endAlpha) {
        this.endAlpha = endAlpha;
    }

    public float getAlphaStep() {
        return alphaStep;
    }

    public void setAlphaStep(float alphaStep) {
        this.alphaStep = alphaStep;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public SpawnShape getSpawnShape() {
        return spawnShape;
    }

    public void setSpawnShape(SpawnShape spawnShape) {
        this.spawnShape = spawnShape;
    }

    public int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
    /**
     * The shape which defines the potential origins for particle spawns.
     */
    private SpawnShape spawnShape = new PointSpawnShape(Vector.ZERO);

    public int getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    public int getMaxParticles() {
        return maxParticles;
    }

    public void setMaxParticles(int maxParticles) {
        this.maxParticles = maxParticles;
    }

    public float getStartSize() {
        return startSize;
    }

    public void setStartSize(float startSize) {
        this.startSize = startSize;
    }

    public float getEndSize() {
        return endSize;
    }

    public void setEndSize(float endSize) {
        this.endSize = endSize;
    }

    public int getStartColor() {
        return startColor;
    }

    public void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public void setEndColor(int endColor) {
        this.endColor = endColor;
    }

    public Vector getStartVelocity(int id) {
        return this.startVelocity;
    }

    public void setStartVelocity(Vector startVelocity) {
        this.startVelocity = startVelocity;
    }

    public Vector getEndVelocity() {
        return endVelocity;
    }

    public void setEndVelocity(Vector endVelocity) {
        this.endVelocity = endVelocity;
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    private Vector velocityStep;
    private int colorStep;

    public void setVelocityStep(Vector velocityStep) {
        this.velocityStep = velocityStep;
    }

    public void setColorStep(int colorStep) {
        this.colorStep = colorStep;
    }

    public void setSizeStep(float sizeStep) {
        this.sizeStep = sizeStep;
    }

    public float getSizeStep() {
        return sizeStep;
    }

    public Vector getVelocityStep() {
        return velocityStep;
    }

    public int getColorStep() {
        return colorStep;
    }

    private float sizeStep;

    private float alphaStep;

    public void updateSteps() {
        this.sizeStep = (endSize - startSize) / (lifespan * 1f);
        this.colorStep = (endColor - startColor) / lifespan;
        this.velocityStep = endVelocity.subtract(startVelocity).divide(lifespan);
        this.alphaStep = (endAlpha - startAlpha) / lifespan;
    }
}

