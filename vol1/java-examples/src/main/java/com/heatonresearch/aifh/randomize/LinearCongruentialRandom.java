package com.heatonresearch.aifh.randomize;

/**
 * Created with IntelliJ IDEA.
 * User: jheaton
 * Date: 7/13/13
 * Time: 4:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinearCongruentialRandom extends AbstractBoxMuller {

    /**
     * First part of default mod.
     */
    public static final long DEFAULT_MOD1 = 2L;

    /**
     * Second part of default mod.
     */
    public static final long DEFAULT_MOD2 = 32L;

    /**
     * Default mult.
     */
    public static final long DEFAULT_MULT = 1103515245L;

    /**
     * Default inc.
     */
    public static final long DEFAULT_INC = 12345L;

    /**
     * The modulus.
     */
    private final long modulus;

    /**
     * The multiplier.
     */
    private final long multiplier;

    /**
     * The amount to increment.
     */
    private final long increment;

    /**
     * The current seed, set to an initial value and always holds the value of
     * the last random number generated.
     */
    private long seed;

    /**
     * The maximum rand number that the standard GCC based LCG will generate.
     */
    public static final long MAX_RAND = 4294967295L;

    /**
     * Construct the default LCG. You need only specify a seed.
     *
     * @param theSeed The seed to use.
     */
    public LinearCongruentialRandom(final long theSeed) {
        this((long) Math.pow(DEFAULT_MOD1, DEFAULT_MOD2),
                DEFAULT_MULT, DEFAULT_INC, theSeed);
    }

    /**
     * Constructor to use a seed equal to system time.
     */
    public LinearCongruentialRandom() {
        this(System.currentTimeMillis());
    }

    /**
     * Create a LCG with the specified modulus, multiplier and increment. Unless
     * you REALLY KNOW WHAT YOU ARE DOING, just use the constructor that just
     * takes a seed. It will set these values to the same as set by the GCC C
     * compiler. Setting these values wrong can create fairly useless random
     * numbers.
     *
     * @param theModulus    The modulus for the LCG algorithm.
     * @param theMultiplier The multiplier for the LCG algorithm.
     * @param theIncrement  The increment for the LCG algorithm.
     * @param theSeed       The seed for the LCG algorithm. Using the same seed will give
     *                      the same random number sequence each time, whether in Java or
     *                      DotNet.
     */
    public LinearCongruentialRandom(final long theModulus,
                                    final long theMultiplier, final long theIncrement,
                                    final long theSeed) {
        super();
        this.modulus = theModulus;
        this.multiplier = theMultiplier;
        this.increment = theIncrement;
        this.seed = theSeed % MAX_RAND;
    }

    /**
     * @return The LCG increment.
     */
    public final long getIncrement() {
        return this.increment;
    }

    /**
     * @return The LCG modulus.
     */
    public final long getModulus() {
        return this.modulus;
    }

    /**
     * @return The LCG multiplier.
     */
    public final long getMultiplier() {
        return this.multiplier;
    }

    /**
     * @return The current seed. Set to a constant to start, thereafter the
     *         previously generated random number.
     */
    public final long getSeed() {
        return this.seed;
    }

    /**
     * @return The next random number as a double between 0 and 1.
     */
    @Override
    public final double nextDouble() {
        double result = (double) nextLong() / LinearCongruentialRandom.MAX_RAND;
        return result;
    }

    /**
     * @return The next random number as a long between 0 and MAX_RAND.
     */
    @Override
    public final long nextLong() {
        this.seed = (this.multiplier * this.seed + this.increment)
                % this.modulus;
        return this.seed;
    }

    @Override
    public boolean nextBoolean() {
        return nextDouble() > 0.5;
    }

    @Override
    public double nextFloat() {
        return (float) nextDouble();
    }

    @Override
    public int nextInt() {
        return (int) nextLong();
    }
}
