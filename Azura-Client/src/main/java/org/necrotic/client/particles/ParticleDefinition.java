package org.necrotic.client.particles;

import org.necrotic.client.graphics.Sprite;

import java.util.Random;

public class ParticleDefinition {

    public static final Random RANDOM = new Random(System.currentTimeMillis());

    private ParticleVector gravity;

    private float startSize = 1f;
    private float endSize = 1f;

    public int particleDepth;

    private int startColor = -1;
    private int endColor = -1;

    private ParticleVector startVelocity = ParticleVector.ZERO;
    private ParticleVector endVelocity = ParticleVector.ZERO;

    private SpawnShape spawnShape = new PointSpawnShape(ParticleVector.ZERO);

    private float startAlpha = 1f;
    private float endAlpha = 0.05f;

    private int lifeSpan = 1;
    private int spawnRate = 1;
    private Sprite sprite;
    private ParticleVector velocityStep;
    private int colorStep;
    private float sizeStep;
    private float alphaStep;

    public static ParticleDefinition[] cache = new ParticleDefinition[]{new ParticleDefinition() {
        { // Master Dungeoneering Cape 0
            setStartVelocity(new ParticleVector(0, -1, 0));
            setEndVelocity(new ParticleVector(0, -1, 0));
            setGravity(new ParticleVector(0, 2 / 4, 0));
            setLifeSpan(40);
            setStartColor(0xff0000);
            setSpawnRate(6);
            setStartSize(1f);
            setEndSize(0);
            setStartAlpha(0.095f);
            updateSteps();
            setColorStep(0x000000);
        }
    }, new ParticleDefinition() { //1
        {
            // Completionist Cape
            setStartVelocity(new ParticleVector(0, -1, 0));
            setEndVelocity(new ParticleVector(0, -1, 0));
            setGravity(new ParticleVector(0, 2 / 4, 0));
            setLifeSpan(10);
            setStartColor(0x990000);
            setSpawnRate(4);
            setStartSize(1.25f);
            setEndSize(0);
            setStartAlpha(0.095f);
            updateSteps();
            setColorStep(0x000900);
        }
    }, new ParticleDefinition() { //2
        { //infernal
            setStartVelocity(new ParticleVector(0, -3, 0)); // x z y
            setEndVelocity(new ParticleVector(0, -3, 0));
            setGravity(new ParticleVector(0, 1 / 2, 0));
            setLifeSpan(19);
            setStartColor(0x990000);
            setSpawnRate(4);
            setStartSize(1f);
            setEndSize(0);
            setStartAlpha(0.095f);
            updateSteps();
            setColorStep(0x000900);
        }
    }, new ParticleDefinition() { //3
        { // wyrm
            setStartVelocity(new ParticleVector(0, 2, 0));
            setEndVelocity(new ParticleVector(0, 2, 0));
            setGravity(new ParticleVector(0, 33 / 2, 0));
            setLifeSpan(19);
            setStartColor(0x000000);
            setSpawnRate(3);
            setStartSize(0.7f);
            setEndSize(0.5f);
            setStartAlpha(0f);
            setEndAlpha(0.035f);
            updateSteps();
            setColorStep(0x000000);
        }
    }, new ParticleDefinition() { //4
        { // wyrm
            setStartVelocity(new ParticleVector(0, 2, 0));
            setEndVelocity(new ParticleVector(0, 2, 0));
            setGravity(new ParticleVector(0, 3 / 2, 0));
            setLifeSpan(19);
            setStartColor(0xFF0800);
            setSpawnRate(4);
            setStartSize(2f);
            setEndSize(0.5f);
            setStartAlpha(0f);
            setEndAlpha(0.045f);
            updateSteps();
            setColorStep(0x000900);
        }
    },
            new ParticleDefinition() { //5
                {
                    // Completionist Cape t
                    setStartVelocity(new ParticleVector(0, -1, 0));
                    setEndVelocity(new ParticleVector(0, -1, 0));
                    setGravity(new ParticleVector(0, 2 / 4, 0));
                    setLifeSpan(19);
                    setStartColor(0xDCC026);
                    setSpawnRate(4);
                    setStartSize(1.25f);
                    setEndSize(0);
                    setStartAlpha(0.095f);
                    updateSteps();
                    setColorStep(0x000000);
                }
            },
            new ParticleDefinition() { //aura, 6
                {
                    setStartVelocity(new ParticleVector(0, 0, 0));
                    setEndVelocity(new ParticleVector(0, 0, 0));
                    setGravity(new ParticleVector(0, 0, 0));
                    setLifeSpan(19);
                    setStartColor(0xffff00);
                    setSpawnRate(4);
                    setStartSize(1.25f);
                    setEndSize(0);
                    setStartAlpha(0.095f);
                    updateSteps();
                    setColorStep(0x000000);
                }
            },
            new ParticleDefinition() {
                { // Tok-Haar Kal 7
                    setStartVelocity(new ParticleVector(0, -3, 0)); // x z y
                    setEndVelocity(new ParticleVector(0, -3, 0));
                    setGravity(new ParticleVector(0, 1 / 2, 0));
                    setLifeSpan(19);
                    setStartColor(0xFF0800);
                    setSpawnRate(1);
                    setStartSize(1f);
                    setEndSize(0);
                    setStartAlpha(0.075f);
                    updateSteps();
                    setColorStep(0x000900);
                }
            },
            new ParticleDefinition() {
                { // Tok-Haar Kal 8
                    setStartVelocity(new ParticleVector(0, -3, 0)); // x z y
                    setEndVelocity(new ParticleVector(0, -3, 0));
                    setGravity(new ParticleVector(0, 1 / 2, 0));
                    setLifeSpan(19);
                    setStartColor(0x009933);
                    setSpawnRate(1);
                    setStartSize(1f);
                    setEndSize(0);
                    setStartAlpha(0.075f);
                    updateSteps();
                    setColorStep(0x000900);
                }
            },
            new ParticleDefinition() {
                { // Tok-Haar Kal 9
                    setStartVelocity(new ParticleVector(0, -3, 0)); // x z y
                    setEndVelocity(new ParticleVector(0, -3, 0));
                    setGravity(new ParticleVector(0, 1 / 2, 0));
                    setLifeSpan(19);
                    setStartColor(0x9933ff);
                    setSpawnRate(1);
                    setStartSize(1f);
                    setEndSize(0);
                    setStartAlpha(0.075f);
                    updateSteps();
                    setColorStep(0x000900);
                }
            },
            new ParticleDefinition() {//10
                { // flame skul
                    setStartVelocity(new ParticleVector(0, 1, 0)); // x z y
                    setEndVelocity(new ParticleVector(0, 1, 0));
                    setGravity(new ParticleVector(0, 1 / 2, 0));
                    setLifeSpan(30);
                    setStartColor(0xDCC026);
                    setSpawnRate(1);
                    setStartSize(2f);
                    setEndSize(0);
                    setStartAlpha(0.025f);
                    updateSteps();
                    setColorStep(0x000900);
                }
            },

            new ParticleDefinition() {//11
                { // red flaming skull
                    setStartVelocity(new ParticleVector(0, 3, 0));
                    setEndVelocity(new ParticleVector(0, 3, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(15);
                    setStartColor(0xFF0000);
                    setSpawnRate(3);
                    setStartSize(1f);
                    setEndSize(0.5f);
                    setStartAlpha(0f);
                    updateSteps();
                    setColorStep(0xFF0000);
                }
            },
            new ParticleDefinition() {//12
                { // blue flaming skull
                    setStartVelocity(new ParticleVector(0, 2, 0));
                    setEndVelocity(new ParticleVector(-1, 2, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(10);
                    setSpawnRate(5);
                    setStartSize(2f);
                    setEndSize(0.5f);
                    setStartAlpha(0f);
                    updateSteps();
                    getSprite();
                    setSprite(new Sprite("smoke1"));
                    setColorStep(0x0000FF);
                }
            },
            new ParticleDefinition() {//13
                { // green flaming skull
                    setStartVelocity(new ParticleVector(0, 3, 0));
                    setEndVelocity(new ParticleVector(0, 3, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(15);
                    setStartColor(0x00ff00);
                    setSpawnRate(10);
                    setStartSize(2f);
                    setEndSize(0.5f);
                    setStartAlpha(0f);
                    updateSteps();
                    setColorStep(0x00ff00);
                }
            },
            new ParticleDefinition() {//14
                { // purple flaming skull
                    setStartVelocity(new ParticleVector(0, -2, 0));
                    setEndVelocity(new ParticleVector(-1, -2, 0));
                    setGravity(new ParticleVector(0, -3 / 2, 0));
                    setLifeSpan(10);
                    setSpawnRate(5);
                    setStartSize(2f);
                    setEndSize(0.5f);
                    setStartAlpha(0f);
                    setSprite(new Sprite("smoke1"));
                    updateSteps();
                    setColorStep(0xf600f6);
                }
            },

            new ParticleDefinition() {//15
                { // maxines sword
                    setStartVelocity(new ParticleVector(0, -1, 0));
                    setEndVelocity(new ParticleVector(0, -1, 0));
                    setGravity(new ParticleVector(0, 2 / 4, 0));
                    setLifeSpan(40);
                    setStartColor(0x0000FF);
                    setSpawnRate(6);
                    setStartSize(1f);
                    setEndSize(0);
                    setStartAlpha(0.095f);
                    updateSteps();
                    setColorStep(0x0000FF);
                }
            },

            new ParticleDefinition() {//16
                { // mysterio boots
                    setStartVelocity(new ParticleVector(0, -1, 0));
                    setEndVelocity(new ParticleVector(0, -1, 0));
                    setGravity(new ParticleVector(0, 2 / 4, 0));
                    setLifeSpan(15);
                    setStartColor(0xffffff);
                    setSpawnRate(4);
                    setStartSize(1f);
                    setEndSize(0);
                    setStartAlpha(0.075f);
                    updateSteps();
                    setColorStep(0xffffff);
                }
            },

            new ParticleDefinition() { //17
                {
                    // Mysterio cape
                    setStartVelocity(new ParticleVector(0, 2, 0));
                    setEndVelocity(new ParticleVector(0, 3, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(19);
                    setStartColor(0xffffff);
                    setSpawnRate(4);
                    setStartSize(1.25f);
                    setEndSize(0);
                    setStartAlpha(0.095f);
                    updateSteps();
                    setColorStep(0xffffff);
                }
            }, new ParticleDefinition() { //18
                {
                    // Mysterio gloves
                    setStartVelocity(new ParticleVector(0, -7, 0));
                    setEndVelocity(new ParticleVector(0, -7, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(15);
                    setStartColor(0xffffff);
                    setSpawnRate(3);
                    setStartSize(1.25f);
                    setEndSize(0);
                    setStartAlpha(0.095f);
                    updateSteps();
                    setColorStep(0xffffff);
                }
            },

            /**
             *
             * Custom definitions here for Raids update
             *
            */

            new ParticleDefinition() {//19
                {//Guardians Helm
                    setStartVelocity(new ParticleVector(0, 4, 0));
                    setEndVelocity(new ParticleVector(0, 4, 0));
                    setGravity(new ParticleVector(0, 2, 0));
                    setLifeSpan(30);
                    setStartColor(0xeeeeee);
                    setSpawnRate(1);
                    setStartSize(1.3f);
                    setEndSize(0);
                    setStartAlpha(0.095f);
                    updateSteps();
                    setColorStep(0xbcbcbc);
                }
            },

            new ParticleDefinition() { //20
                {//Guardians Gloves
                    setStartVelocity(new ParticleVector(0, -4, 0));
                    setEndVelocity(new ParticleVector(0, -4, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(30);
                    setStartColor(0x02a012);
                    setSpawnRate(3);
                    setStartSize(2f);
                    setEndSize(0);
                    setStartAlpha(0.095f);
                    updateSteps();
                    setColorStep(0x02a012);
                }
            },

            new ParticleDefinition() {//21
                {//Guardians Boots
                    setStartVelocity(new ParticleVector(0, -1, 0));
                    setEndVelocity(new ParticleVector(0, -1, 0));
                    setGravity(new ParticleVector(0, 2 / 4, 0));
                    setLifeSpan(15);
                    setStartColor(0x6ee4e4);
                    setSpawnRate(4);
                    setStartSize(1f);
                    setEndSize(0);
                    setStartAlpha(0.075f);
                    updateSteps();
                    setColorStep(0x300b8b);
                }
            },

            new ParticleDefinition() {//22
                {//Diabolic Mage T3 Wing
                    setStartVelocity(new ParticleVector(0, -7, 0));
                    setEndVelocity(new ParticleVector(0, -7, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(14);
                    setStartColor(0x090000);
                    setSpawnRate(5);
                    setStartSize(1f);
                    setEndSize(0.3f);
                    setStartAlpha(0.025f);
                    updateSteps();
                    setColorStep(0xab1818);
                }
            },
            new ParticleDefinition() {//23
                {//Diabolic Mage T3 Wing
                    setStartVelocity(new ParticleVector(0, 1, 0));
                    setEndVelocity(new ParticleVector(0, 2, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(14);
                    setStartColor(0x090000);
                    setSpawnRate(5);
                    setStartSize(2f);
                    setEndSize(0.4f);
                    setStartAlpha(0.025f);
                    updateSteps();
                    setColorStep(0xab1818);
                }
            },
            new ParticleDefinition() {//24
                {//Diabolic Mage T3 Wing
                    setStartVelocity(new ParticleVector(0, 1, 0));
                    setEndVelocity(new ParticleVector(0, 2, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(14);
                    setStartColor(0x090000);
                    setSpawnRate(5);
                    setStartSize(2f);
                    setEndSize(0.4f);
                    setStartAlpha(0.025f);
                    updateSteps();
                    setColorStep(0xab1818);
                }
            },
            new ParticleDefinition() {//25
                {//Diabolic Mage T3 Staff Up and Out
                    setStartVelocity(new ParticleVector(0, 1, 0));
                    setEndVelocity(new ParticleVector(1, 2, 0));
                    setGravity(new ParticleVector(1, 3 / 2, 0));
                    setLifeSpan(14);
                    setStartColor(0x090000);
                    setSpawnRate(5);
                    setStartSize(1f);
                    setEndSize(0.4f);
                    setStartAlpha(0.035f);
                    updateSteps();
                    setColorStep(0xab1818);
                }
            },
            new ParticleDefinition() {//26
                {//Diabolic Mage T3 Staff Up and Out
                    setStartVelocity(new ParticleVector(0, -1, 0));
                    setEndVelocity(new ParticleVector(-1, -2, 0));
                    setGravity(new ParticleVector(-1, 2 / 3, 0));
                    setLifeSpan(14);
                    setStartColor(0x090000);
                    setSpawnRate(5);
                    setStartSize(1f);
                    setEndSize(0.4f);
                    setStartAlpha(0.035f);
                    updateSteps();
                    setColorStep(0xab1818);
                }
            },
            new ParticleDefinition() {//27
                {//Diabolic Mage T3 Wing
                    setStartVelocity(new ParticleVector(0, -7, 0));
                    setEndVelocity(new ParticleVector(0, -7, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(14);
                    setStartColor(0x090000);
                    setSpawnRate(5);
                    setStartSize(1.6f);
                    setEndSize(0.7f);
                    setStartAlpha(0.025f);
                    updateSteps();
                    setColorStep(0xab1818);//090000
                }
            },
            new ParticleDefinition() {//28
                {//Forgotten Trinity
                    setStartVelocity(new ParticleVector(0, 1, 0));
                    setEndVelocity(new ParticleVector(1, 2, 0));
                    setGravity(new ParticleVector(1, 3 / 2, 0));
                    setLifeSpan(14);
                    setStartColor(0xffffff);
                    setSpawnRate(5);
                    setStartSize(1f);
                    setEndSize(0.7f);
                    setStartAlpha(0.035f);
                    updateSteps();
                    setColorStep(0xf2ac27);
                }
            },

            new ParticleDefinition() {//29
                { //Mysterio
                    setStartVelocity(new ParticleVector(0, -7, 0));
                    setEndVelocity(new ParticleVector(0, -7, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(9);
                    setStartColor(0x00ff00);
                    setSpawnRate(7);
                    setStartSize(0.5f);
                    setEndSize(2f);
                    setStartAlpha(0.6f);
                    updateSteps();
                    setColorStep(0x00ff00);
                }
            },

            new ParticleDefinition() {//30
                { // gold flaming skull
                    setStartVelocity(new ParticleVector(0, 3, 0));
                    setEndVelocity(new ParticleVector(0, 3, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(15);
                    setStartColor(0xffff00);
                    setSpawnRate(12);
                    setStartSize(2f);
                    setEndSize(0.5f);
                    setStartAlpha(0f);
                    updateSteps();
                    setColorStep(0xffff00);
                }
            },

            new ParticleDefinition() {//31
                { // h'ween flaming skull
                    setStartVelocity(new ParticleVector(0, 3, 0));
                    setEndVelocity(new ParticleVector(0, 3, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(15);
                    setStartColor(0xFF9966);
                    setSpawnRate(12);
                    setStartSize(2f);
                    setEndSize(0.5f);
                    setStartAlpha(0f);
                    updateSteps();
                    setColorStep(0xFF9966);
                }
            },

            new ParticleDefinition() {//32
                { // h'ween flaming skull black
                    setStartVelocity(new ParticleVector(0, 3, 0));
                    setEndVelocity(new ParticleVector(0, 3, 0));
                    setGravity(new ParticleVector(0, 3 / 2, 0));
                    setLifeSpan(15);
                    setStartColor(0x000000);
                    setSpawnRate(12);
                    setStartSize(2f);
                    setEndSize(0.5f);
                    setStartAlpha(0f);
                    updateSteps();
                    setColorStep(0x000000);
                }
            },


    };


    public final SpawnShape getSpawnedShape() {
        return spawnShape;
    }

    public final float getStartAlpha() {
        return startAlpha;
    }

    public final void setStartAlpha(float startAlpha) {
        this.startAlpha = startAlpha;
    }

    public final float getAlphaStep() {
        return alphaStep;
    }

    public final Sprite getSprite() {
        return sprite;
    }

    public final void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public final int getSpawnRate() {
        return this.spawnRate;
    }

    public final void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    public final void setStartSize(float startSize) {
        this.startSize = startSize;
    }

    public final float getStartSize() {
        return startSize;
    }

    public float getEndSize() {
        return endSize;
    }

    public int getEndColor() {
        return endColor;
    }

    public final void setEndSize(float endSize) {
        this.endSize = endSize;
    }

    public final int getStartColor() {
        return startColor;
    }

    public final void setStartColor(int startColor) {
        this.startColor = startColor;
    }

    public int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public final ParticleVector getStartVelocity(int id) {
        switch (id) {
            default:
                return new ParticleVector(this.startVelocity.getX() + randomWithRange(-1, 1), this.startVelocity.getY() + randomWithRange(0, 0), this.startVelocity.getZ() + randomWithRange(-1, 1));
        }
    }

    public ParticleVector getGravity() {
        return gravity;
    }

    public void setGravity(ParticleVector gravity) {
        this.gravity = gravity;
    }

    public final void setStartVelocity(ParticleVector startVelocity) {
        this.startVelocity = startVelocity;
    }

    public ParticleVector getEndVelocity() {
        return endVelocity;
    }

    public final void setEndVelocity(ParticleVector endVelocity) {
        this.endVelocity = endVelocity;
    }

    public final int getLifeSpan() {
        return lifeSpan;
    }

    public final void setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public final void setColorStep(int colorStep) {
        this.colorStep = colorStep;
    }

    public void setEndAlpha(float endAlpha) {
        this.endAlpha = endAlpha;
    }

    public final float getSizeStep() {
        return sizeStep;
    }

    public final ParticleVector getVelocityStep() {
        return velocityStep;
    }

    public final int getColorStep() {
        return colorStep;
    }

    public final void updateSteps() {
        this.sizeStep = (endSize - startSize) / (lifeSpan * 1f);
        this.colorStep = (endColor - startColor) / lifeSpan;
        this.velocityStep = endVelocity.subtract(startVelocity).divide(lifeSpan);
        this.alphaStep = (endAlpha - startAlpha) / lifeSpan;
    }
}