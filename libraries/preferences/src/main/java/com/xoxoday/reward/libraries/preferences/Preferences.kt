package com.xoxoday.reward.libraries.preferences

import android.content.Context

/**
 * Interface for accessing and modifying preference data returned by
 * [Context.getSharedPreferences]
 *
 * Created by Harsh Mittal on 01/07/22.
 **/
interface Preferences {

    /**
     * Retrieve a boolean value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param fallback Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a boolean.
     */
    fun getBoolean(key: String, fallback: Boolean = false): Boolean

    /**
     * Retrieve an int value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param fallback Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     */
    fun getInt(key: String, fallback: Int = 0): Int

    /**
     * Retrieve a long value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param fallback Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a long.
     */
    fun getLong(key: String, fallback: Long = 0): Long

    /**
     * Retrieve a String value from the preferences.
     *
     * @param key The name of the preference to retrieve.
     * @param fallback Value to return if this preference does not exist.
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a String.
     */
    fun getString(key: String, fallback: String? = null): String?

    fun getStringSet(key: String, fallback: Set<String>? = null): Set<String>?

    /**
     * Set a *_boolean_* value in the preferences editor, to be written back after
     * [android.content.SharedPreferences.Editor.commit] is called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun saveBoolean(key: String, value: Boolean = false)

    /**
     * Set a *_integer_* value in the preferences editor, to be written back after
     * [android.content.SharedPreferences.Editor.commit] is called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun saveInt(key: String, value: Int = 0)

    /**
     * Set a *_long_* value in the preferences editor, to be written back after
     * [android.content.SharedPreferences.Editor.commit] is called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     */
    fun saveLong(key: String, value: Long = 0)

    /**
     * Set a *_string_* value in the preferences editor, to be written back after
     * [android.content.SharedPreferences.Editor.commit] is called.
     *
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.  Passing __*_null_*__
     * for this argument is equivalent to calling [delete] with this key.
     */
    fun saveString(key: String, value: String? = null)

    fun saveStringSet(key: String, values: Set<String>? = null)

    /**
     * Method to delete the preference value based on the given [key].
     *
     * @param key The name of the preference to remove.
     */
    fun delete(key: String)

    fun contains(key: String): Boolean

    /**
     * Removes __all__ the values from the preferences.
     */
    fun clear()

    /**
     * Removes __all__ the values from the preferences except the given [keys].
     */
    fun clearAllExcept(vararg keys: String)
}