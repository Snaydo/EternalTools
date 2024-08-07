# EternalTools

[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Spigot Version](https://img.shields.io/badge/Spigot-1.8.8-orange.svg)](https://www.spigotmc.org/)

EternalTools est un plugin Spigot innovant permettant aux joueurs de posséder des outils et des armes indestructibles. Offrez à vos joueurs une expérience unique avec des items éternels qui ne peuvent jamais être perdus ou détruits.

## Fonctionnalités

- **Outils et Armes Indestructibles** : Gardez vos items préférés pour toujours.
- **Enchantements Personnalisés** : Ajoutez des enchantements puissants et uniques.
- **Lore et Noms Personnalisés** : Donnez une touche unique à vos items.
- **Conservation des Items après la Mort** : Ne perdez jamais vos items éternels en mourant.
- **Commandes Utiles** : Gérez facilement vos items et reconfigurez votre plugin.
- **Messages de Permission Personnalisables** : Adaptez les messages à votre serveur.

## Configuration

Modifiez le fichier `config.yml` pour personnaliser les items éternels :

```yaml
eternal-tools:
  - id: 1
    name: "§f✦ §c§lÉpée de Zeus §f✦"
    Material: "DIAMOND_SWORD"
    Lore:
      - ""
      - "§cRareté: §6✭✭✭✭✭"
      - ""
      - "§7Cette épée ne peut jamais être perdue !"
      - ""
    Enchantments:
      - enchantment: "DAMAGE_ALL"
        level: 5
      - enchantment: "FIRE_ASPECT"
        level: 2
    Unbreakable: true
  - id: 2
    name: "§fArc Éternel"
    Material: "BOW"
    Lore:
      - ""
      - "§cRareté: §6✭✭✭✭✭"
      - ""
      - "§7Cet arc ne peut jamais être perdu !"
      - ""
    Enchantments:
      - enchantment: "FLAME"
        level: 1
      - enchantment: "PUNCH"
        level: 2
      - enchantment: "ARROW_INFINITE"
        level: 1
    Unbreakable: true
  - id: 3
    name: "§fPelle Éternelle"
    Material: "DIAMOND_SHOVEL"
    Lore:
      - ""
      - "§cRareté: §6✭✭✭✭✭"
      - ""
      - "§7Cette pelle ne peut jamais être perdue !"
      - ""
    Enchantments:
      - enchantment: "EFFICIENCY"
        level: 5
    Unbreakable: true
  - id: 4
    name: "§fHache Éternelle"
    Material: "DIAMOND_AXE"
    Lore:
      - ""
      - "§cRareté: §6✭✭✭✭✭"
      - ""
      - "§7Cette hache ne peut jamais être perdue !"
      - ""
    Enchantments:
      - enchantment: "EFFICIENCY"
        level: 5
    Unbreakable: true

permission-messages:
  default: "§cVous n'avez pas la permission d'utiliser cette commande."
