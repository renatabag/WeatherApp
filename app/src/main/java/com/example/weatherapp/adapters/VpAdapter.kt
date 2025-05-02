// Файл: VpAdapter.kt
// Назначение: Адаптер для управления фрагментами в ViewPager2

package com.example.weatherapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class VpAdapter(
    fa: FragmentActivity,          // Активность-хост
    private val list: List<Fragment> // Список фрагментов
) : FragmentStateAdapter(fa) {

    // Создает фрагмент для указанной позиции
    override fun createFragment(position: Int): Fragment {
        return list[position]      // Возвращает фрагмент из списка
    }

    // Возвращает общее количество элементов
    override fun getItemCount(): Int {
        return list.size           // Размер списка фрагментов
    }
}